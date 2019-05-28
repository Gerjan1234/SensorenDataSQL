import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * class ControleNewFile - geef hier een beschrijving van deze class
 *
 * @author (Gerjan)
 * @version (23 April 2019)
 */

public class RepoUitlezen
{
    private ArrayList<String> repodata;

    /**
     * Constructor voor objects van class repodata
     * aanmaken arrayList en mehtode starten
     */
    public RepoUitlezen()
    {
        repodata = new ArrayList<>();
        CHeckfileAllLines();
    }

    /**
     * het bestand data .repo in /home/pi/projectX wordt uitgelezen.
     * de regels die beginnen met '#' worden uit de Arraylist verwijderd
     */
    public void CHeckfileAllLines()
    {
        Path fileLocation = Paths.get("/usr/","data.repo");
        Charset charset = Charset.forName("ISO-8859-1");
        try {
            List<String> lines = Files.readAllLines(fileLocation, charset);
            for (String line : lines) {
                repodata.add(line);
            }
            repodata.removeIf(String -> String.charAt(0) == '#');
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Methode om de losse regels uit het repo bestand weer te geven.
     *
     * @param Arraynummber input voor regel nummer uit het repo bestand (zonder de '#')
     * @return    deze method geeft eens String terug (zin uit de repo file)
     */
    public String getRepodata(int Arraynummber)
    {
        return repodata.get(Arraynummber);
    }

}

