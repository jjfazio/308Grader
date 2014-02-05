package scripts;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import model.spreadsheet.CourseDB;
import model.spreadsheet.CourseInfo;

/**
 * Script for creating a file with student data.
 * This data will be used for the download roster action in the
 * menu.
 * @author jamesfazio
 *
 */
public class StudentDBScript
{
    private static final String FILE_NAME = "studentDB.txt";
    private static int studentCount = 154;
    
    public static void main (String[] args)
    {
        createDBFile();
    }
    
    /**
     * Creates the text file with student data. Each line
     * of the file contains a student user name, first name,
     * last name, id, major, grade level and an SIS course 
     * they will appear on the roster for.
     */
    public static void createDBFile()
    {
        PrintWriter writer = null;
        CourseDB courseDB = CourseDB.getInstance();
        List<CourseInfo> courses = courseDB.getAllCourses();
        Random rand = new Random();
        CourseInfo course;
        File file;
        StringBuilder sb;
        
        try
        {
            file = new File(FILE_NAME);
            
            if (!file.exists())
                file.createNewFile();
            writer = new PrintWriter(file);
            
            for (int i = 0; i < studentCount; i++)
            {
                course = courses.get(rand.nextInt(courses.size()));
                sb = new StringBuilder();
                sb.append(userNames[i] + "|");
                sb.append(firstNames[i] + "|");
                sb.append(lastNames[i] + "|");
                sb.append("" + i + "|");
                sb.append(majors[rand.nextInt(majors.length)] + "|");
                sb.append(gradeLevels[rand.nextInt(gradeLevels.length)] + "|");
                sb.append(course.getCourseName() + "|");
                sb.append(course.getQuarter()  + "|");
                sb.append(course.getSection()  + "|");
                sb.append(course.getNumber() + "|");
                sb.append(course.getDept());
                
                writer.println(sb);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally 
        {
            if (writer != null)
                writer.close();
        }
    }
    
    private final static String[] gradeLevels = 
        {
        "Freshman",
        "Sophmore",
        "Junior",
        "Senior"
        };
    
    private final static String[] majors =
        {
        "Software Engineering",
        "Computer Science",
        "Computer Engineering",
        "Electrical Engineering",
        };
    
    
    private final static String[] lastNames =
        {
        "adams",
        "allen",
        "allison",
        "alvarez",
        "anderson",
        "andrews",
        "armstrong",
        "arnold",
        "avila",
        "bailey",
        "baker",
        "barnes",
        "bennett",
        "bishop",
        "boyd",
        "bradley",
        "brooks",
        "brown",
        "bryan",
        "burke",
        "burton",
        "butler",
        "campbell",
        "carlson",
        "carr",
        "carter",
        "chase",
        "chen",
        "christensen",
        "clark",
        "collins",
        "comer",
        "cook",
        "cooper",
        "cox",
        "crawford",
        "cunningham",
        "davis",
        "day",
        "dean",
        "dickinson",
        "edwards",
        "elliott",
        "ellis",
        "evans",
        "fischer",
        "fisher",
        "fong",
        "ford",
        "freeman",
        "frost",
        "garcia",
        "gardner",
        "gomes",
        "gomez",
        "gonzales",
        "graham",
        "green",
        "griffin",
        "hall",
        "hamilton",
        "hansen",
        "hanson",
        "harris",
        "harrison",
        "hartman",
        "harvey",
        "hayes",
        "henderson",
        "henry",
        "hernandez",
        "hill",
        "holmes",
        "howard",
        "hughes",
        "hunt",
        "jackson",
        "jensen",
        "johnson",
        "jones",
        "keller",
        "kelley",
        "kennedy",
        "king",
        "lane",
        "long",
        "lopez",
        "marsh",
        "martinez",
        "mathews",
        "matthews",
        "mcdonald",
        "miller",
        "mitchell",
        "moore",
        "morgan",
        "morris",
        "nelson",
        "newton",
        "obrien",
        "oconnor",
        "olsen",
        "palmer",
        "parker",
        "patterson",
        "peck",
        "perkins",
        "perry",
        "peterson",
        "phillips",
        "powell",
        "price",
        "randolph",
        "reed",
        "rice",
        "richardson",
        "rivera",
        "rivers",
        "roberts",
        "robinson",
        "rodrigues",
        "rodriguez",
        "rogers",
        "romero",
        "ruis",
        "sanders",
        "santos",
        "shaw",
        "short",
        "silva",
        "simpson",
        "smith",
        "snyder",
        "spafford",
        "spencer",
        "springer",
        "stafford",
        "stewart",
        "stone",
        "sullivan",
        "taylor",
        "thompson",
        "turner",
        "walker",
        "wallace",
        "walton",
        "ward",
        "warner",
        "watson",
        "weber",
        "wells",
        "white",
        "williams",
        "wong",
        "wood",
        "woods",
        "wright",
        };
    
    private final static String[] firstNames =
        {
        "aaccf",
        "aalders",
        "aaren",
        "aarika",
        "aaron",
        "aartjan",
        "aasen",
        "ab",
        "abacus",
        "abadines",
        "abagael",
        "abagail",
        "abahri",
        "abasolo",
        "abazari",
        "abba",
        "abbai",
        "abbas",
        "abbatant",
        "abbate",
        "abbe",
        "abbey",
        "abbi",
        "abbie",
        "abbot",
        "abbott",
        "abby",
        "abbye",
        "abdalla",
        "abdallah",
        "abdel",
        "abdel-az",
        "abdel-ma",
        "abdel-ra",
        "abdel-sa",
        "abdelazi",
        "abdelmad",
        "abdelrah",
        "abdelran",
        "abdelsal",
        "abderrao",
        "abderraz",
        "abdi",
        "abdo",
        "abdollah",
        "abdolrah",
        "abdou",
        "abdrani",
        "abdul",
        "abdul-az",
        "abdul-ma",
        "abdul-no",
        "abdul-ra",
        "abdul-sa",
        "abdulazi",
        "abdulla",
        "abdullah",
        "abdulmad",
        "abdulrah",
        "abdulran",
        "abdulsal",
        "abdur",
        "abe",
        "abedi",
        "abel",
        "abelard",
        "abell",
        "abella",
        "abellera",
        "abello",
        "abelow",
        "abernath",
        "aberneth",
        "abeu",
        "abey",
        "abhay",
        "abhijit",
        "abi-aad",
        "abid",
        "abie",
        "abigael",
        "abigail",
        "abigale",
        "abike",
        "abner",
        "abou-arr",
        "abou-ezz",
        "aboul-ma",
        "aboussou",
        "abovyan",
        "abra",
        "abraham",
        "abrahan",
        "abrahim",
        "abram",
        "abramo",
        "abrams",
        "abran",
        "abrar",
        "absi",
        "abu",
        "abul",
        "access",
        "accounti",
        "acelvari",
        "achal",
        "achamma",
        "acharyya",
        "achcar",
        "achille",
        "achkar",
        "achmad",
        "ackaouy",
        "acker",
        "acklin",
        "ackwood",
        "acree",
        "acres",
        "acs",
        "action",
        "actionte",
        "acton",
        "aczel",
        "ad",
        "ada",
        "adah",
        "adahm",
        "adair",
        "adal",
        "adaline",
        "adam",
        "adamczyk",
        "adamkows",
        "adamo",
        "adamowic",
        "adams",
        "adamski",
        "adamson",
        "adamyk",
        "adan",
        "adara",
        "adcock",
        "adcox",
        "adda",
        "addetia",
        "addi",
        "addia",
        "addie",
        "addison",
        "addona",
        "addons",
        "addy",
        "ade",
        "adebayo",
        "adel",
        "adela",
        "bill",
        };
    
    private final static String[] userNames = 
        {
        "1000cupcakes",
        "11Echo",
        "123marine2",
        "1panda80",
        "21texans",
        "3DS_Triforce",
        "3rdbaseman4",
        "459pm",
        "4everfuzzy",
        "4lphax",
        "4sordy",
        "77chainblade",
        "99milzman",
        "Aaron_Rainthief",
        "ABQiu",
        "absthatsme",
        "abysmus",
        "abzilla",
        "acidsin",
        "acraftybugger",
        "adambuss",
        "adampoconnor",
        "adamzetti",
        "adolia",
        "Adriedupleaf",
        "Aerinyes",
        "aetherandnether",
        "AFRICA",
        "aidanrocks25",
        "ailbhlaffe",
        "Ainieve",
        "Akathepriest",
        "Alec97531",
        "alecf731",
        "alexryanb",
        "alexthecoolmac",
        "Alizatina",
        "Allyvand1497",
        "alphaelf",
        "Amortik1996",
        "Anactofgod",
        "andrewf731",
        "Andy586",
        "AndyWestside",
        "angrysquirlz",
        "Anhysbys",
        "Anthony423",
        "antz666",
        "aperry1993",
        "ApocalyqseNow",
        "Aqua2iK",
        "aqwshalew3",
        "ArinaChan23",
        "Armydude101",
        "Artemis315",
        "Artemis_En",
        "articuno96",
        "asbo96",
        "Ashmen",
        "asmith307",
        "aTbagger",
        "atlanta0",
        "atyl95",
        "AustinSpiers",
        "Avengeline",
        "Awdie",
        "AwesomeDude728",
        "AwesomeGuy900",
        "awesomeguy911",
        "Awsomr00",
        "ayrtonchin",
        "Azasimus",
        "azureheights",
        "B1GBADW0IF",
        "b777all",
        "babyface0519",
        "bailout00",
        "baller111",
        "Banaynay",
        "bandrew97",
        "Barbaric_Emu",
        "basstardee",
        "Batyote",
        "Bball_Star",
        "bbrooks066",
        "Belaneth",
        "Ben952",
        "Benben582",
        "Benn_Benn",
        "benpowell987",
        "bevo00",
        "bgeaman",
        "Bigalow40",
        "BigGojira",
        "BigKid_Icarus",
        "BigLlama",
        "BigMucho",
        "Bigtimewinner",
        "BIG_Quakez",
        "birdman15",
        "birdy9",
        "bizznchriz",
        "Bkettell",
        "bkwopper",
        "blackdust79",
        "blackfire9z",
        "blackhawk77g",
        "blackouTT",
        "blackstorm58",
        "blake11120",
        "BlastedFool",
        "blazindonuts824",
        "BleakBear",
        "bleh337",
        "Blimm",
        "Blitzfitz",
        "BlockedShots",
        "Bloodlest",
        "Bloodnight",
        "Bloodtyl",
        "blurrdew",
        "bluzekluze",
        "bmanpyro",
        "BMXBikes58",
        "bob20163",
        "bob6199",
        "bobbshields",
        "Boborito71",
        "booglee322",
        "bookboy123",
        "bootgamer5",
        "boriater",
        "Boss_Pro",
        "bowledd",
        "boxofspiderss",
        "BoxPuncher",
        "brampel",
        "brancher",
        "brandt2846",
        "Braxis",
        "BrianOwennn",
        "BRISKET",
        "broadsword123",
        "brobot222",
        "BroncoBuster25",
        "BrookesGirl",
        "brycecameron",
        "BrylieGirl",
        "bsams5",
        "BTechnique",
        "BTT8",
        "bubabang12",
        "budro1111",
        "BugsyH",
        "buildingharry",
        };

}
