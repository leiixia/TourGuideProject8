package tourGuide.service;

import gpsUtil.GpsUtil;
import tourGuide.user.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GpsUtilService {
    private final ExecutorService executorService = Executors.newFixedThreadPool(10000);
    private final GpsUtil gpsUtil;

    public GpsUtilService() {
        this.gpsUtil = new GpsUtil();
    }

    public void submitLocation(User user, UserService userService){
        CompletableFuture.supplyAsync(() -> {
                    return gpsUtil.getUserLocation(user.getUserId());
                }, executorService)
                .thenAccept(visitedLocation -> { userService.finalizeLocation(user, visitedLocation); });
    }

    }
}
