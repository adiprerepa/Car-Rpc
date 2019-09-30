//package com.prerepa.rpc_algorithm;
//
//import java.util.ArrayList;
//
///*
//Basically recursively traverse and check for
//closer or farther with checkConsent with
//distance formula and traverse in that direction
//base case: reached dest.
// */
//class Controller {
//
//    Coordinates curCoords;
//    Coordinates destCoords;
//
//    ArrayList<Obstacle> obstacleList;
//
//    Controller(Coordinates coordinates, ArrayList<Obstacle> obstacleList) {
//        this.curCoords = coordinates;
//        this.obstacleList = obstacleList;
//    }
//
//    boolean traverse(TraverseOption option) {
//        Coordinates prevCoords = curCoords;
//        if (option.equals(TraverseOption.UP)) {
//            curCoords.longit += 1;
//        } else if (option.equals(TraverseOption.DOWN)){
//            curCoords.longit -= 1;
//        } else if (option.equals(TraverseOption.LEFT)) {
//            curCoords.lat -= 1;
//        } else if (option.equals(TraverseOption.RIGHT)) {
//            curCoords.lat += 1;
//        }
//        DistanceStatus status = checkConsent(prevCoords);
//        if (status == DistanceStatus.CLOSER) {
//            traverse(option);
//        } else if (status == DistanceStatus.FARTHER) {
//            if (option.equals(TraverseOption.LAT)) {
//                traverse(TraverseOption..);
//            } else {
//                traverse(TraverseOption.LAT);
//            }
//        }
//
//    }
//
//    private DistanceStatus checkConsent(Coordinates pastCoordinates) {
//        double flooredPastLat = Math.floor(pastCoordinates.lat);
//        double flooredPastLong = Math.floor(pastCoordinates.longit);
//        double floorCurLat = Math.floor(curCoords.lat);
//        double floorCurLong = Math.floor(curCoords.longit);
//        Coordinates flooredPast = new Coordinates();
//        Coordinates flooredCur = new Coordinates();
//        flooredPast.longit = flooredPastLong;
//        flooredPast.lat = flooredPastLat;
//        flooredCur.lat = floorCurLat;
//        flooredCur.longit = floorCurLong;
//        if (distFormula(flooredPast, flooredPast) > distFormula(flooredCur)) {
//            return DistanceStatus.CLOSER;
//        } else if (distFormula(flooredPast) < distFormula(flooredCur)) {
//            return DistanceStatus.FARTHER;
//        } else {
//            return DistanceStatus.REACH;
//        }
//    }
//
//    double distFormula(Coordinates curCoords, Coordinates destCoords) {
//        double deltaX = destCoords.lat - curCoords.lat;
//        double deltaY = destCoords.longit - curCoords.longit;
//        return Math.pow(deltaX, deltaX) - Math.pow(deltaY, deltaY);
//    }
//
//    enum DistanceStatus {
//        CLOSER,
//        FARTHER,
//        REACH
//    }
//
//    enum TraverseOption {
//        LEFT,
//        RIGHT,
//        DOWN,
//        UP
//    }
//}
