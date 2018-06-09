package de.jos.service.command.commandservice.database.model;


import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String apiKey;
    private String currentProjectName;
    private String currentProjectId;
    private String currentServiceName;
    private String currentServiceId;
    private LinkedList<UserSettings> shortcuts = new LinkedList<>();

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String id, String apiToken, String projectName, String projectId, String serviceName, String serviceId) {
        this.id = id;
        this.apiKey = apiToken;
        this.currentProjectId = projectId;
        this.currentProjectName = projectName;
        this.currentServiceId = serviceId;
        this.currentServiceName = serviceName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCurrentProjectName() {
        return currentProjectName;
    }

    public void setCurrentProjectName(String currentProjectName) {
        this.currentProjectName = currentProjectName;
    }

    public String getCurrentProjectId() {
        return currentProjectId;
    }

    public void setCurrentProjectId(String currentProjectId) {
        this.currentProjectId = currentProjectId;
    }

    public String getCurrentServiceName() {
        return currentServiceName;
    }

    public void setCurrentServiceName(String currentServiceName) {
        this.currentServiceName = currentServiceName;
    }

    public String getCurrentServiceId() {
        return currentServiceId;
    }

    public void setCurrentServiceId(String currentServiceId) {
        this.currentServiceId = currentServiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addShortcut(UserSettings userSettings) {
        this.shortcuts.add(userSettings);
    }

    public LinkedList<UserSettings> getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(LinkedList<UserSettings> shortcuts) {
        this.shortcuts = shortcuts;
    }

    public UserSettings getCurrentSettingsAsUserSettings() {
        return new UserSettings(currentProjectId, currentServiceId);
    }

    public String toReplyString() {
        String project;
        String service;
        if (currentProjectId == null) {
            project = "You didn't set a project yet!";
        } else {
            project = "Your current project: " + currentProjectName + "; " + currentProjectId;
        }
        if (this.currentServiceId == null) {
            service = "You didn't set a service yet!";
        } else {
            service = "Your current service: " + currentServiceName + "; " + currentServiceId;
        }
        return project + "\n" + service;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", currentProjectName='" + currentProjectName + '\'' +
                ", currentProjectId='" + currentProjectId + '\'' +
                ", currentServiceName='" + currentServiceName + '\'' +
                ", currentServiceId='" + currentServiceId + '\'' +
                ", shortcuts=" + shortcuts +
                '}';
    }
}
