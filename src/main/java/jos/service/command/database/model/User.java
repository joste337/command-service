package jos.service.command.database.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

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
    private ArrayList<UserSettings> shortcuts = new ArrayList<>();

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

    public ArrayList<UserSettings> getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(ArrayList<UserSettings> shortcuts) {
        this.shortcuts = shortcuts;
    }

    public UserSettings getCurrentSettingsAsUserSettings() {
        return new UserSettings(currentProjectId, currentServiceId);
    }

    public String getStatus() {
        StringBuilder status = new StringBuilder();
        status.append("Yout settings: \n");
        if (currentProjectId != null) {
            status.append("Your current project: ").append(currentProjectName).append("; ").append(currentProjectId).append("\n");
        }
        if (currentServiceId != null) {
            status.append("Your current service: ").append(currentServiceName).append("; ").append(currentServiceId).append("\n");
        }
        if (!shortcuts.isEmpty()) {
            status.append("Your shortcuts: \n");
            shortcuts.forEach(userSetting -> status.append(userSetting.getKey()).append("; for project ").append(userSetting.getProjectName()).append(" and service").append(userSetting.getServiceName()));
        }
        return status.toString();
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
