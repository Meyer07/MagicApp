public class Player {
    private int life;
    private int[] commanderDamage;
    private int poisonCount;
    private String name;
    private boolean isDead;
    
    public Player(int startingLife, String name) {
        this.life = startingLife;
        this.setName(name);
        this.isDead = false;
    }
    
    public void numPlayers(int s){
        commanderDamage = new int[s];
    }
    
    public int getLife() {
        return this.life;
    }
    
    private void setName(String s) {
        if(this.isDead)
            return;
        this.name = s;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void gainLife(int l) {
        if(this.isDead)
            return;
        this.life+=l;
    }
    
    public void takeDamage(int damage) {
        if(this.isDead)
            return;
        this.life -= damage;
        this.checkLoss();
        if(this.isDead) 
            System.out.println(name+ " has ran out of life");
    }
    
    public void takeCommanderDamage(int comDamage, int p) {
        if(this.isDead)
            return;
        this.commanderDamage[p] += comDamage;
        this.takeDamage(comDamage);
    }
    
    public void removeCommanderDamage(int comDamage, int p) {
        this.commanderDamage[p] -= comDamage;
        this.gainLife(comDamage);
        if(this.commanderDamage[p] < 0) 
            this.commanderDamage[p] = 0;
    }
    
    public void takePoison(int p) {
        if(this.isDead) 
            return;
        this.poisonCount += p;
        this.checkLoss();
        if(this.isDead)
            System.out.println(name + " has ran out of life");
    }
    
    public void removePoison(int p) {
        if(this.isDead)
            return;
        this.poisonCount -= p;
        if(this.poisonCount < 0)
            this.poisonCount = 0;
    }
    
    public boolean getisDead() {
        return this.isDead;
    }
    
    public void checkLoss() {
        if(this.isDead)
            return;
        if(this.life<=0 || poisonCount>=10) 
            this.isDead = true;
        for(int i = 0; i < commanderDamage.length;i++){
            if(commanderDamage[i] >= 21){
                this.isDead = true;
            }
        }
    }
    
    public void printPlayerState() {
        if(this.getisDead()){
            System.out.println(name + "\nIs Dead\n");
            return;
        }
        System.out.println(name + "\nLife:    " + this.getLife());
        for(int i = 0; i < this.commanderDamage.length;i++){
        	if(this.commanderDamage[i]>0) 
        		System.out.println("Comander Damage:  " + this.commanderDamage[i]);
        }
        if(this.poisonCount>0)
        	System.out.println("Poison Counter:   " + this.poisonCount + "\n");
        
    }
}