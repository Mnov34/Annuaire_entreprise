package com.mnov34.CUBES4solo.controller;

import com.mnov34.CUBES4solo.annotation.FXMLController;
import com.mnov34.CUBES4solo.api.ApiClient;
import com.mnov34.CUBES4solo.model.Site;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Maël NOUVEL <br>
 * 11/03/2025
 **/
@FXMLController
@Slf4j
public class SiteFXController implements Initializable {

    @FXML
    private TableView<Site> siteTable;
    @FXML
    private TableColumn<Site, String> siteIdColumn, siteNameColumn;
    @FXML
    private TableColumn<Site, Void> editColumn, deleteColumn;
    @FXML
    private Button addButton;

    @FXML
    private TextField siteNameField;

    private Site selectedSite;

    private final ApiClient siteApiService;
    private final ObservableList<Site> sites = FXCollections.observableArrayList();

    @Autowired
    public SiteFXController(Retrofit retrofit) {
        this.siteApiService = retrofit.create(ApiClient.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableColumns();
        refreshData();
        setupCellFactories();
    }

    private void setupTableColumns() {
        siteIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        siteNameColumn.setCellValueFactory(new PropertyValueFactory<>("site"));
        siteTable.setItems(sites);
    }

    private void setupCellFactories() {
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(event -> {
                    Site site = getTableView().getItems().get(getIndex());
                    populateFormForEditing(site);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : editButton);
            }
        });

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Site site = getTableView().getItems().get(getIndex());
                    handleDeleteSite(site);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteButton);
            }
        });
    }

    private void populateFormForEditing(Site site) {
        selectedSite = site;
        Platform.runLater(() -> {
            siteNameField.setText(site.getSite());
            addButton.setText("Update");
        });
    }

    private void refreshData() {
        siteApiService.getSites().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Platform.runLater(() -> {
                        sites.setAll(response.body());
                        selectedSite = null;
                        addButton.setText("Ajouter");
                    });
                } else {
                    showError("Failed to delete site", new RuntimeException("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Site>> call, Throwable t) {
                showError("Failed to load site", t);
            }
        });
    }

    @FXML
    private void handleAddSite() {
        if (selectedSite == null) {
            addSite();
        } else {
            updateSite();
        }
    }

    private void addSite() {
        Site newSite = new Site();
        newSite.setSite(siteNameField.getText());

        siteApiService.createSite(newSite).enqueue(new Callback<Site>() {
            @Override
            public void onResponse(Call<Site> call, Response<Site> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> {
                        refreshData();
                        clearForm();
                    });
                } else {
                    showError("Failed to add site", new RuntimeException("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Site> call, Throwable t) {
                showError("Failed to add site", t);
            }
        });
    }

    private void updateSite() {
        if (selectedSite == null) return;
        selectedSite.setSite(siteNameField.getText());

        siteApiService.updateSite(selectedSite.getId(), selectedSite).enqueue(new Callback<Site>() {
            @Override
            public void onResponse(Call<Site> call, Response<Site> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> {
                        refreshData();
                        clearForm();
                    });
                } else {
                    showError("Failed to update site", new RuntimeException("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Site> call, Throwable t) {
                showError("Failed to update site", t);
            }
        });
    }

    private void handleDeleteSite(Site site) {
        siteApiService.deleteSite(site.getId()).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Platform.runLater(() -> refreshData());
                } else {
                    showError("Failed to delete site", new RuntimeException("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showError("Failed to delete site", t);
            }
        });
    }

    private void clearForm() {
        Platform.runLater(() -> {
            siteNameField.clear();
            selectedSite = null;
            addButton.setText("Ajouter");
        });
    }

    private void showError(String message, Throwable throwable) {
        if (Platform.isFxApplicationThread()) {
            log.error(message, throwable);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(message);
            alert.setContentText(throwable.getMessage());
            alert.showAndWait();
        } else {
            Platform.runLater(() -> showError(message, throwable));
        }
    }
}
