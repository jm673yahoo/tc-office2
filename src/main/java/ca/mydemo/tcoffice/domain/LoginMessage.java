package ca.mydemo.tcoffice.domain;

public class LoginMessage {
    private boolean allowMultiLingual;
    private String versionAndBuild;
    private String errorMessage;

    public boolean isAllowMultiLingual() {
        return allowMultiLingual;
    }


    public void setAllowMultiLingual(boolean allowMultiLingual) {
        this.allowMultiLingual = allowMultiLingual;
    }

    public String getVersionAndBuild() {
        return versionAndBuild;
    }

    public void setVersionAndBuild(String versionAndBuild) {
        this.versionAndBuild = versionAndBuild;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
