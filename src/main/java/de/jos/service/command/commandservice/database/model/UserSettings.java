package de.jos.service.command.commandservice.database.model;


import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class UserSettings implements Serializable{
    @Id
    private String key;
    private String projectId;
    private String projectName;
    private String serviceId;
    private String serviceName;

    public UserSettings() {
    }

    public UserSettings(String key, String projectId, String projectName, String serviceId, String serviceName) {
        this.key = key;
        this.projectId = projectId;
        this.projectName = projectName;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    public UserSettings(String projectId, String serviceId) {
        this.projectId = projectId;
        this.serviceId = serviceId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "UserSettings{" +
                "key='" + key + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
