public class ArmyFactory {

    public Army getArmy(String armyName) {
        if (armyName == null) {
            return null;
        }
        if (armyName.equalsIgnoreCase("red")) {
            return new BuildArmy("red");
        } else if (armyName.equalsIgnoreCase("blue")) {
            return new BuildArmy("blue");
        }
        return null;
    }
}
