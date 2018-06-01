package de.jos.service.command.commandservice.database.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String apiKey;
    private String projectName;
    private String projectId;
    private String serviceName;
    private String serviceId;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String id, String apiToken, String projectName, String projectId, String serviceName, String serviceId) {
        this.id = id;
        this.apiKey = apiToken;
        this.projectId = projectId;
        this.projectName = projectName;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toReplyString() {
        String project;
        String service;
        if (projectId == null) {
            project = "You didn't set a project yet!";
        } else {
            project = "Your current project: " + projectName + "; " + projectId;
        }
        if (this.serviceId == null) {
            service = "You didn't set a service yet!";
        } else {
            service = "Your current service: " + serviceName + "; " + serviceId;
        }
        return project + "\n" + service;
    }
}
