package com.clickergame.core;

import java.util.HashMap;
import com.clickergame.systems.GoldenCookie;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GoldenCookieSystem {
    public HashMap<String, GoldenCookie> goldenCookieList = new HashMap<String, GoldenCookie>();
    private CriticalSystem criticalSystem;
    private GameState gameState;
    private ScheduledExecutorService timer;
    public GoldenCookie activeCookie = null; 
    public double cookieChance = 1.0d;

    public GoldenCookieSystem(GameState gameState, CriticalSystem criticalSystem) {
        this.gameState = gameState;
        this.criticalSystem = criticalSystem;

        goldenCookieList.put("golden1", new GoldenCookie(10, 30, "+10% Critical Chance for 30s!", "critChance"));
        goldenCookieList.put("golden2", new GoldenCookie(10, 10, "10x Critical Power for 10s!", "critPower"));
        goldenCookieList.put("golden3", new GoldenCookie(100, 5, "100% Critical Chance for 5s!", "critChance"));
        goldenCookieList.put("golden4", new GoldenCookie(10, 0, "FREE COOKIES!", "cookies"));
    }

    // Getter
    public double GetCookieChance() { return cookieChance; };
    

    public void updateGoldenCookies() {
        if (activeCookie == null) {
            stopTimer();
            return;
        }

        activeCookie.decreaseLifespan();
        if (activeCookie.GetLifespan() <= 0) {
            removeEffect(activeCookie);
            activeCookie = null;
            stopTimer();
        }
    }

    public void activateCookie(GoldenCookie cookie) {
        activeCookie = cookie;
        cookie.resetLifespan(cookie.GetLifespan());

        switch (cookie.GetTypeId()) {
            case "critChance":
                criticalSystem.criticalChance += cookie.GetValue();
                break;
            case "critPower":
                criticalSystem.criticalPower *= cookie.GetValue();
                break;
            case "cookies":
                gameState.coins += gameState.GetCoins() * 0.10;
                activeCookie = null;
                return;
            default:
                break;
        }

        startTimer();
    }

    public void removeEffect(GoldenCookie cookie) {
        switch (cookie.GetTypeId()) {
            case "critChance":
                criticalSystem.criticalChance -= cookie.GetValue();
                break;
            case "critPower":
                criticalSystem.criticalPower /= cookie.GetValue();
                break;
            default:
                break;
        }
    }

    private void startTimer() {
        if (timer == null || timer.isShutdown()) {
            if ( activeCookie.GetTypeId() == "critChance" ) {
            } else if (activeCookie.GetTypeId() == "critPower" ) {
            }
            timer = Executors.newSingleThreadScheduledExecutor();
            timer.scheduleAtFixedRate(this::updateGoldenCookies, 1, 1, TimeUnit.SECONDS);
        }
    }

    public void stopTimer() {
        if (timer != null && !timer.isShutdown()) {
            timer.shutdown();
        }
    }
    
}
