package model.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;

public class StudentDB
{
    private static int studentCount = 157;
    private Gradebook gradebook;
    private Map<SpreadsheetCourse, List<Student>> courseStudentMap;
    private static StudentDB instance;
    
    
    
    private StudentDB()
    {
        gradebook = Gradebook.getInstance();
        courseStudentMap = new HashMap<SpreadsheetCourse, List<Student>>();
        generateStudents();
    }
    
    public static StudentDB getInstance() {
        if (instance == null)
           new StudentDB();
        
        return instance;
     }
    
    public List<Student> getStudentsForClass(SpreadsheetCourse course)
    {
        return courseStudentMap.get(course);
    }
    
    private void generateStudents()
    {
        SpreadsheetCourse course;
        Student student;
        int count;
        List<SpreadsheetCourse> courses = gradebook.getCourses();
        List<Student> studentList;
        Random majorRand = new Random(majors.length);
        Random gradeLevel = new Random(gradeLevels.length);
        Random courseRand = new Random(courses.size());
        
        for (count = 0; count < studentCount; count++)
        {
            course = courses.get(courseRand.nextInt());
            student = new Student(userNames[count], firstNames[count],
                    lastNames[count], count + "", majors[majorRand.nextInt()],
                    gradeLevels[gradeLevel.nextInt()]);
            
            if (!courseStudentMap.containsKey(course))
            {
                studentList = new ArrayList<Student>();
            }
            else 
            {
                studentList = courseStudentMap.get(course);
            }
            
            studentList.add(student);
            courseStudentMap.put(course, studentList);
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