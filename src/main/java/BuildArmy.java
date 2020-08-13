import java.util.HashSet;
import java.util.Set;

public class BuildArmy implements Army {

    CSVReader reader;

    Set <ArmyCorp> builtArmy = new HashSet<>();

    ArmyCorp corpToAdd;

    public BuildArmy(String armyName) {
        reader = new CSVReader("c:/" + armyName + ".txt");
        String[] armyString = reader.army;
        System.out.print(armyString.length);
        int numberOfCorps = (armyString.length / 7);
        int offset = 0;
        for (int i = 1; i <= (numberOfCorps); i++) {
            int a = Integer.parseInt(armyString[0 + offset]);
            int cav = Integer.parseInt(armyString[1 + offset]);
            int inf = Integer.parseInt(armyString[2 + offset]);
            String lead = armyString[4 + offset];
            int mor = Integer.parseInt(armyString[6 + offset]);
            int pos = Integer.parseInt(armyString[3 + offset]);
            String side = armyString[5 + offset];
            corpToAdd = new ArmyCorp(a, cav, inf, lead, mor, pos, side);
            builtArmy.add(corpToAdd);
            offset = offset + 7;
        }
    }

    @Override
    public void displayArmy() {
        for (ArmyCorp corps : builtArmy) {
            System.out.println(corps.toString());
        }
    }

    @Override
    public String displayArmyTextBox() {
        String returnArmyString = "";
        for (ArmyCorp corps : builtArmy) {
            returnArmyString = returnArmyString + corps.toString() + "\n";
        }
        return returnArmyString;
    }

    public Set <ArmyCorp> listOfCorp() {
        return this.builtArmy;
    }
}
