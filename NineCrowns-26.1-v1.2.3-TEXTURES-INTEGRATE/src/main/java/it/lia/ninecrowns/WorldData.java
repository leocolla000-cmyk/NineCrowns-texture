package it.lia.ninecrowns;
import com.google.gson.*;
import java.nio.file.*;
import java.util.*;
public final class WorldData {
    public final Roster roster=new Roster();
    private final Map<UUID,Long> lightning=new HashMap<>();
    private final Path path;
    public WorldData(Path path){
        this.path=path;
        if(!Files.exists(path)) return;
        try{
            var root=JsonParser.parseString(Files.readString(path)).getAsJsonObject();
            if(root.has("players")) for(var e:root.getAsJsonObject("players").entrySet()) roster.register(UUID.fromString(e.getKey()),e.getValue().getAsString());
            if(root.has("lightning")) for(var e:root.getAsJsonObject("lightning").entrySet()) lightning.put(UUID.fromString(e.getKey()),e.getValue().getAsLong());
        }catch(Exception e){ throw new IllegalStateException("Nine Crowns world data error",e); }
    }
    public long remaining(UUID id,long now){ return Math.max(0,lightning.getOrDefault(id,0L)-now); }
    public void cooldown(UUID id,long expiry){ lightning.put(id,expiry); save(); }
    public void save(){
        try{
            var root=new JsonObject(); var ps=new JsonObject(); var cds=new JsonObject();
            roster.players().forEach((id,n)->ps.addProperty(id.toString(),n));
            lightning.forEach((id,t)->cds.addProperty(id.toString(),t));
            root.add("players",ps); root.add("lightning",cds);
            Files.createDirectories(path.getParent()); Files.writeString(path,new GsonBuilder().setPrettyPrinting().create().toJson(root));
        }catch(Exception e){ throw new IllegalStateException("Nine Crowns save error",e); }
    }
}
