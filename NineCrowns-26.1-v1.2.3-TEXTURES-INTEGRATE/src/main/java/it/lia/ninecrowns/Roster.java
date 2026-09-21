package it.lia.ninecrowns;
import java.util.*;
public final class Roster {
    public static final int SIZE = 9;
    private final LinkedHashMap<UUID,String> players = new LinkedHashMap<>();
    public boolean register(UUID id,String name){
        if(players.containsKey(id)){ players.put(id,name); return true; }
        if(players.size()>=SIZE) return false;
        players.put(id,name); return true;
    }
    public boolean contains(UUID id){ return players.containsKey(id); }
    public Map<UUID,String> players(){ return Collections.unmodifiableMap(players); }
    public boolean validHeads(UUID crafter,List<UUID> heads){
        if(players.size()!=SIZE || !players.containsKey(crafter) || heads.size()!=8) return false;
        Set<UUID> expected=new HashSet<>(players.keySet()); expected.remove(crafter);
        return new HashSet<>(heads).size()==8 && new HashSet<>(heads).equals(expected);
    }
}
