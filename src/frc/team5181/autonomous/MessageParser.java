package frc.team5181.autonomous;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Created by TylerLiu on 2018/01/22.
 * @author Jaci Brunning
 */
public class MessageParser {

    /**
     * Game features including the SWITCH and SCALE. Near denotes close
     * to the alliance wall and FAR denotes furthest from the alliance
     * wall
     */
    public enum GameFeature {
        SWITCH_NEAR, SCALE, SWITCH_FAR
    }

    /**
     * OwnedSide defines which side (from the perspective of the alliance
     * station) is owned by the alliance during the match. This is one of
     * LEFT, RIGHT or UNKNOWN (the latter in the case where game data
     * is not yet made available by the FMS or DS)
     */
    public enum OwnedSide {
        UNKNOWN, LEFT, RIGHT, NOT_MATCH
    }

    /**
     * Determine the OwnedSide of any given GameFeature. Use this method to
     * determine which PLATE of each feature (SCALE or SWITCH) is OWNED by your
     * ALLIANCE. Perspectives are referenced relative to your ALLIANCE STATION.
     *
     * @param feature The feature to get the owned side for. See GameFeature.
     * @return  The Owned Side (PLATE) of the feature. See OwnedSide. Make sure to
     *          check for UNKNOWN.
     */
    public static OwnedSide getOwnedSide(GameFeature feature) {
        if (!DriverStation.getInstance().isFMSAttached()) return OwnedSide.NOT_MATCH;
        String gsm = DriverStation.getInstance().getGameSpecificMessage();

        // If the length is less than 3, it's not valid. Longer than 3 is permitted, but only
        // the first 3 characters are taken.
        if (gsm == null || gsm.length() < 3)
            return OwnedSide.UNKNOWN;

        int index = feature.ordinal();

        char gd = gsm.charAt(index);
        switch (gd) {
            case 'L':
            case 'l':
                return OwnedSide.LEFT;
            case 'R':
            case 'r':
                return OwnedSide.RIGHT;
            default:
                return OwnedSide.UNKNOWN;
        }
    }

}
