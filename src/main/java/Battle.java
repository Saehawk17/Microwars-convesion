
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stuart Rendall
 */
public class Battle {
    
    private Set <ArmyCorp> battleCorps;
    private int redStrength ;
    private int blueStrength;
    private ArmyCorp testCorp;
    public Battle(Set <ArmyCorp> bc)
    {
        battleCorps= new HashSet(bc);
        calcTotalStrength();
        
    }
    
    /*
    *calculate the strength of the units on both sides in battle
    */
    public void calcTotalStrength()
    {
        redStrength=0;
        blueStrength=0;
       Iterator <ArmyCorp> it = battleCorps.iterator();
       while(it.hasNext())
       {
          testCorp=it.next();
          if(testCorp.getLeader().equals("French"))
          {
              redStrength=testCorp.calculateHitPoints();
          }
          if(testCorp.getLeader().equals("Allies"))
          {
              blueStrength=testCorp.calculateHitPoints();
          }
          
          
       }
    }
    public int getBlueStrength()
    {
        return this.blueStrength;
    }
    public int getRedStrength()
            {
                return this.redStrength;
            }
    public Set <ArmyCorp> getCorpsInBattle()
    {
        return this.battleCorps;
    }
    public String printCorpsInBattle()
    {
        String returnString="Battle Corps ";
        System.out.print(battleCorps.size()+ "\n");
        Iterator <ArmyCorp> bc = battleCorps.iterator();
        while(bc.hasNext())
        {
            ArmyCorp a = bc.next();
            
            returnString =returnString+ a.toString()+"\n";
            
        }
        return returnString;
    }
    
}
