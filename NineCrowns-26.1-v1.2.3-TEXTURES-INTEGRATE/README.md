# Nine Crowns 1.2.3 — Minecraft Java 26.1 / Fabric

Questa cartella è già pronta per GitHub Actions.

## IMPORTANTISSIMO: caricamento su GitHub

Dopo aver estratto `NineCrowns-26.1-v1.2.3-CLEAN.zip`, apri la cartella estratta e carica **il suo contenuto** nella root del repository.

Nella pagina principale del repository devi vedere direttamente:

- `.github`
- `src`
- `build.gradle`
- `settings.gradle`
- `gradle.properties`
- `README.md`

Non deve esserci una cartella extra che contiene questi file.

Il workflow è già incluso in `.github/workflows/main.yml`. Non creare "Java with Gradle" e non creare altri workflow.

Vai su **Actions → Build NineCrowns → Run workflow**. Se termina con il tick verde, scarica l'Artifact `NineCrowns-26.1-v1.2.3-VERIFIED`.

Il workflow rifiuta automaticamente una struttura caricata male o un JAR vuoto.

## Modifiche richieste

- Lancia OP basata su **Netherite Spear**.
- Spada OP: **20% Poison I** e **10% Slowness I** per 3 secondi a ogni colpo valido.
- Corona dell'Imperatore: **Protection VIII, Unbreaking III, Mending, Speed II, Strength II e Fire Resistance I** mentre è indossata.
- Registrazione automatica dei primi 9 giocatori tramite UUID.
- Drop della testa alla morte.
- Ricette OP richiedono esattamente le teste degli altri 8 partecipanti.


## Texture integrate (v1.2.3)
- Spada OP: texture integrata da `spada.zip`.
- Lancia OP: texture integrata da `Lightning_Spear_TexturePack_Fabric_26.1.zip`.
- Corona dell'Imperatore: texture oggetto + texture indossata integrate da `corona.zip`.
- Corona Vuota: texture oggetto + texture indossata integrate da `corona_grezza(1).zip`.
- Le corone restano veri oggetti equipaggiabili nello slot testa: la texture non modifica la logica `Equippable`.
