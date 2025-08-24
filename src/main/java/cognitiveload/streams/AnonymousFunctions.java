package cognitiveload.streams;

import cognitiveload.Device;

import java.time.LocalDate;
import java.util.List;

public class AnonymousFunctions {

    public static void main(String[] args) {

    }

    /*
       Objective: filter out devices with firmware older than 2023-12-12R3
       The below showcases an example where we get higher cognitive load. The
       anonymous function is filtering on valid firmware versions, but you
       don't immediately know that. The intention of the code is not clear,
       which results in a bit higher cognitive load. The example is still simple
       here, to make the point more clear.
     */
    public static List<Device> filterOutDevicesWithInvalidFirmwareVersions() {
        var devices = List.of(
                new Device(1L, "2023-09-10R7"),
                new Device(2L, "2023-11-29R21"),
                new Device(3L, "2023-12-12R2"),
                new Device(4L, "2023-12-112R4")
        );

        var validFirmwareVersion = "2023-12-12R3";

        return devices.stream()
                .filter(device -> {
                    var currentFirmwareParsed = device.getCurrentFirmwareVersion().split("R");
                    var validFirmwareParsed = validFirmwareVersion.split("R");

                    var currentFirmwareDateParts = currentFirmwareParsed[0].split("-");
                    var currentFirmwareDate = LocalDate
                            .of(Integer.parseInt(currentFirmwareDateParts[0]),
                                    Integer.parseInt(currentFirmwareDateParts[1]),
                                    Integer.parseInt(currentFirmwareDateParts[2]));

                    var validFirmwareDateParts = validFirmwareParsed[0].split("-");
                    var validFirmwareDate = LocalDate
                            .of(Integer.parseInt(validFirmwareDateParts[0]),
                                    Integer.parseInt(validFirmwareDateParts[1]),
                                    Integer.parseInt(validFirmwareDateParts[2]));

                    if (currentFirmwareDate.equals(validFirmwareDate)) {
                        if (currentFirmwareParsed.length > 1) {
                            return Integer.parseInt(currentFirmwareParsed[1]) >= Integer.parseInt(validFirmwareParsed[1]);
                        }
                    }
                    return false;
                })
                .toList();
    }

    /*
       Objective: filter out devices with firmware older than 2023-12-12R3
       The below showcases a better example already. The stream of operations is
       more readable. We removed the details of what needs to be done
       and we put a meaningful abstraction in the place. We can understand what is
       going on just reading the abstraction.
       The counterpart is that we have removed the details. So if we really want
       to know what exactly our program is going to do, we need to find these
       details. They are now in a separate function
     */
    public static List<Device> filteringMadeEasy() {
        var devices = List.of(
                new Device(1L, "2023-09-10R7"),
                new Device(2L, "2023-11-29R21"),
                new Device(3L, "2023-12-12R2"),
                new Device(4L, "2023-12-112R4")
        );

        return devices.stream()
                .filter(AnonymousFunctions::hasValidFirmware)
                .toList();
    }

    public static boolean hasValidFirmware(Device currentDevice) {
        var validFirmwareVersion = "2023-12-12R3";
        var currentFirmwareParsed = currentDevice.getCurrentFirmwareVersion().split("R");
        var validFirmwareParsed = validFirmwareVersion.split("R");

        var currentFirmwareDateParts = currentFirmwareParsed[0].split("-");
        var currentFirmwareDate = LocalDate
                .of(Integer.parseInt(currentFirmwareDateParts[0]),
                        Integer.parseInt(currentFirmwareDateParts[1]),
                        Integer.parseInt(currentFirmwareDateParts[2]));

        var validFirmwareDateParts = validFirmwareParsed[0].split("-");
        var validFirmwareDate = LocalDate
                .of(Integer.parseInt(validFirmwareDateParts[0]),
                        Integer.parseInt(validFirmwareDateParts[1]),
                        Integer.parseInt(validFirmwareDateParts[2]));

        if (currentFirmwareDate.equals(validFirmwareDate)) {
            if (currentFirmwareParsed.length > 1) {
                return Integer.parseInt(currentFirmwareParsed[1]) >= Integer.parseInt(validFirmwareParsed[1]);
            }
        }
        return false;
    }

    /*
       Objective: filter out devices with firmware older than 2023-12-12R3
       The below showcases an example where we get higher cognitive load. The
       anonymous function is filtering on valid firmware versions, but you
       don't immediately know that. The intention of the code is not clear,
       which results in a bit higher cognitive load. The example is still simple
       here, to make the point more clear.
     */
    public static List<Device> bestWayOfFiltering() {
        var devices = List.of(
                new Device(1L, "2023-09-10R7"),
                new Device(2L, "2023-11-29R21"),
                new Device(3L, "2023-12-12R2"),
                new Device(4L, "2023-12-112R4")
        );

        return devices.stream()
                .filter(Device::hasValidFirmware)
                .toList();
    }

}
