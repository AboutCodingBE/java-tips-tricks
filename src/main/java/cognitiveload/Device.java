package cognitiveload;

import java.time.LocalDate;

public class Device {

    private static final String VALID_FIRMWARE_VERSION = "2023-09-10R2";
    public static final String REVISION_DELIMITER = "R";

    private Long id;
    private String currentFirmwareVersion;
    private String status = "";

    public Device(Long id, String currentFirmwareVersion) {
        this.id = id;
        this.currentFirmwareVersion = currentFirmwareVersion;
    }

    public String getCurrentFirmwareVersion() {
        return currentFirmwareVersion;
    }

    public void setCurrentFirmwareVersion(String currentFirmwareVersion) {
        this.currentFirmwareVersion = currentFirmwareVersion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasValidFirmware() {
        var currentFirmwareParsed = this.getCurrentFirmwareVersion().split(REVISION_DELIMITER);
        var validFirmwareParsed = VALID_FIRMWARE_VERSION.split(REVISION_DELIMITER);

        var currenFirmwareDate = parseDateFrom(currentFirmwareParsed[0]);
        var validFirmwareDate = parseDateFrom(validFirmwareParsed[0]);

        if (currenFirmwareDate.equals(validFirmwareDate)) {
            if (currentFirmwareParsed.length > 1) {
                return Integer.parseInt(currentFirmwareParsed[1]) >= Integer.parseInt(validFirmwareParsed[1]);
            }
        }
        return false;
    }

    private LocalDate parseDateFrom(String dateString) {
        var parts = dateString.split("-");
        return LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }
}
