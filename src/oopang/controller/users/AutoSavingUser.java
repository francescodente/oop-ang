package oopang.controller.users;

import java.util.function.Consumer;

import oopang.commons.RequestRunner;
import oopang.commons.events.Event;
import oopang.model.powers.PowerTag;

public class AutoSavingUser implements User {

    private static final double SAVE_WAIT_TIME = 2.0;
    private final User user;
    private final Consumer<User> saveAction;
    private RequestRunner<User> saver;

    /**
     * @param user
     * @param saveAction
     */
    public AutoSavingUser(final User user, final Consumer<User> saveAction) {
        super();
        this.user = user;
        this.saveAction = saveAction;
    }
    
    private void requestSave() {
        if (this.saver == null || !this.saver.isAlive()) {
            this.saver = new RequestRunner<User>(this.user, this.saveAction, SAVE_WAIT_TIME);
            this.saver.start();
        }
        this.saver.sendRequest();
    }

    @Override
    public String getName() {
        synchronized (this.user) {
            return this.user.getName();
        }
    }

    @Override
    public int getXpPoints() {
        synchronized (this.user) {
            return this.user.getXpPoints();
        }
    }

    @Override
    public void addXpPoints(final int points) {
        synchronized (this.user) {
            this.user.addXpPoints(points);
            this.requestSave();
        }
    }

    @Override
    public int getCoins() {
        synchronized (this.user) {
            return this.user.getCoins();
        }
    }

    @Override
    public void changeCoins(final int amount) {
        synchronized (this.user) {
            this.user.changeCoins(amount);
            this.requestSave();
        }
    }

    @Override
    public int getStatValue(final UserStat stat) {
        synchronized (this.user) {
            return this.user.getStatValue(stat);
        }
    }

    @Override
    public void setStatValue(final UserStat stat, final int value) {
        synchronized (this.user) {
            this.user.setStatValue(stat, value);
            this.requestSave();
        }
    }

    @Override
    public void setPowerLevel(final PowerTag power, final int level) {
        synchronized (this.user) {
            this.user.setPowerLevel(power, level);
            this.requestSave();
        }
    }

    @Override
    public int getPowerLevel(final PowerTag power) {
        synchronized (this.user) {
            return this.user.getPowerLevel(power);
        }
    }

    @Override
    public Event<User> getUserChangedEvent() {
        synchronized (this.user) {
            return this.user.getUserChangedEvent();
        }
    }
}
