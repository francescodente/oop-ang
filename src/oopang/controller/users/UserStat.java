package oopang.controller.users;

public enum UserStat {
    MAX_ARCADE_SCORE,
    MAX_SURVIVAL_SCORE,
    MAX_ARCADE_STAGE,
    MAX_SURVIVAL_STAGE;
    
    public int getDefaultValue() {
        return 0;
    }
}
