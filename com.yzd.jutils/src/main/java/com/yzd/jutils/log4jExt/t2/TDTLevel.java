package com.yzd.jutils.log4jExt.t2;

/**
 * @author: yaozhendong
 * @create: 2019-11-08 09:28
 **/


import org.apache.log4j.Level;

/**
 * @author sunnylocus
 * @project MRMAutoloc
 * @vresion 1.0 2009-7-22
 * @description 自定义级别REMIND，该级别用来发送提醒邮件,级别要比INFO低
 */
public class TDTLevel extends Level {
    private static final long serialVersionUID = 7288304330257085144L;

    static public final int REMIND_INT = Level.INFO_INT - 1;
    static public final int LETHAL_INT = Level.FATAL_INT + 1;

    private static String REMIND_STR = "REMIND";
    private static String LETHAL_STR = "LETHAL";

    public static final TDTLevel REMIND = new TDTLevel(REMIND_INT, REMIND_STR, 7);
    public static final TDTLevel LETHAL = new TDTLevel(LETHAL_INT, LETHAL_STR, 0);

    protected TDTLevel(int level, String strLevel, int syslogEquiv) {
        super(level, strLevel, syslogEquiv);
    }

    /**
     * Convert the string passed as argument to a level. If the conversion
     * fails, then this method returns {@link #REMIND}.
     */
    public static Level toLevel(String sArg) {
        return (Level) toLevel(sArg, TDTLevel.REMIND);
    }

    public static Level toLevel(String sArg, Level defaultValue) {
        if (sArg == null) {
            return defaultValue;
        }
        String stringVal = sArg.toUpperCase();

        if (stringVal.equals(REMIND_STR)) {
            return TDTLevel.REMIND;
        } else if (stringVal.equals(LETHAL_STR)) {
            return TDTLevel.LETHAL;
        }

        return Level.toLevel(sArg, (Level) defaultValue);
    }

    public static Level toLevel(int i) throws IllegalArgumentException {
        switch (i) {
            case REMIND_INT:
                return TDTLevel.REMIND;
            case LETHAL_INT:
                return TDTLevel.LETHAL;
        }
        return Level.toLevel(i);
    }

}