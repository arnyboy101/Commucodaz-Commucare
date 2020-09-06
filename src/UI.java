import org.apache.poi.ss.usermodel.*;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.*;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import javax.swing.*;
import javafx.application.Application;
import javafx.stage.Stage;
import org.jdesktop.swingx.util.Contract;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;
import java.awt.event.*;

public class UI implements ItemListener, ActionListener {

 static JFrame f1 = new JFrame("Commucare");
 static Color green_munsell = new Color(19, 194, 124);
 static Color red_pigment = new Color(252, 38, 38);
 static Color w = new Color(255, 255, 255);
 static JPanel p1 = new JPanel();
 static Font font = new Font("SanSerif", Font.BOLD, 48);
 static JComboBox dropDownOne;
 static JLabel l2 = new JLabel();
 static String symptoms[] = new String[10];
 static int i = -1;
 static JLabel l3 = new JLabel();
 static JButton b = new JButton("Diagnose!");
 static String disease[];
 static FileInputStream file;
 static XSSFWorkbook workbook;
 static JLabel l8 = new JLabel("Kindly select your symptoms in the drop down menu given below-");
static JLabel l9 = new JLabel("Your most probable symptoms are-");
static JLabel l10 = new JLabel("Once, you click the 'Diagnose!' button,/n you will recieve a list of five probable diseases along with treatments");




 static String s[] = {
  "  ",
  "Fever",
  "Increased sweating",
  "Spontaneous rupture of membranes",
  "Cough",
  "Decreased body weight",
  "Chill",
  "Diarrhea",
  "Patient non compliance",
  "Muscle hypotonia",
  "Feeling suicidal",
  "Mass of body structure",
  "Lesion",
  "Constipation",
  "Fremitus",
  "Decreased stool caliber",
  "Satiety early",
  "Hematochezia",
  "Egophony",
  "Cicatrisation",
  "Pain abdominal",
  "Flatulence",
  "Large-for-dates fetus",
  "Vomitting",
  "Lung nodule",
  "Breech presentation",
  "Shortness of breath",
  "Immobile",
  "Unsteady gait",
  "Hallucinations visual",
  "Extreme exhaustion",
  "Sleeplessness",
  "Enuresis",
  "Feeling hopeless",
  "Prostatism",
  "Drool",
  "Agitation",
  "Nightmare",
  "Pin-point pupils",
  "Frail",
  "Tremor resting",
  "Hyperkalemia",
  "Facial paresis",
  "Groggy",
  "Muscle twitch",
  "Wheelchair bound",
  "Tremor",
  "Blood in stools",
  "Discolouration on skin",
  "Tumor cell invasion",
  "Haemorrhage",
  "Fatigue",
  "Back pain",
  "Orthostasis",
  "Hyponatremia",
  "Dizziness",
  "Arthralgia",
  "Swelling",
  "Transaminitis",
  "Clonus",
  "Aphagia",
  "Paralyse",
  "Low back pain",
  "Charleyhorse",
  "Wheezing",
  "Flushing",
  "Indifferent mood",
  "Urinoma",
  "Distended abdomen",
  "Hypoalbuminemia",
  "Pustule",
  "Abdominal tenderness",
  "Exhaustion",
  "Energy increased",
  "Suicidal",
  "Irritable mood",
  "Has religious belief",
  "Disturbed family",
  "Hallucinations auditory",
  "Verbal auditory hallucinations",
  "Weepiness",
  "Behavior hyperactive",
  "Catatonia",
  "Hypersomnia",
  "Difficulty",
  "Hyperhidrosis disorder",
  "Mydriasis",
  "Extrapyramidal sign",
  "Loose associations",
  "Intoxication",
  "Motor retardation",
  "Homelessness",
  "Blackout",
  "Throat sore",
  "Hepatosplenomegaly",
  "Coughing blood",
  "Snuffle",
  "Hacking cough",
  "Stridor",
  "Headache",
  "Photophobia",
  "Painful swallowing",
  "Poor dentition",
  "Adverse reaction",
  "Abdominal bloating",
  "Thicken",
  "Hoarseness",
  "General discomfort",
  "Metastatic lesion",
  "Unhappy",
  "Paresthesia",
  "Gravida 0",
  "Sore to touch",
  "Heartburn",
  "Nausea",
  "Reduced body movement",
  "Chest pain/tightness",
  "Yellow phlegm",
  "Dyspnea on exertion",
  "Left atrial hypertrophy",
  "Weight gain",
  "Erythema",
  "Redness",
  "Abscess bacterial",
  "Hypesthesia",
  "Hyperacusis",
  "Pruritus",
  "Scratch marks",
  "Moan",
  "Murphy's sign",
  "Colic abdominal",
  "Ascites",
  "Qt interval prolonged",
  "Cardiovascular finding",
  "Sinus rhythm",
  "Gasping for breath",
  "Feces in rectum",
  "Abnormally hard consistency",
  "Cushingoid facies",
  "Apyrexial",
  "Hoard",
  "Neologism",
  "Seizure",
  "Unconscious state",
  "Panic",
  "Oliguria",
  "Sputum purulent",
  "Hypoxemia",
  "Hypercapnia",
  "Splenomegaly",
  "Bleeding of vagina",
  "Green phlegm",
  "Nausea and vomiting",
  "Awakening early",
  "Tenesmus",
  "Urge incontinence",
  "Lethargy",
  "Speech slurred",
  "Asterixis",
  "Sleepy",
  "Dysarthria",
  "Lightheadedness",
  "Systolic murmur",
  "Pain in lower limb",
  "Cardiomegaly",
  "Hypotension",
  "Titubation",
  "Spasm",
  "Dysdiadochokinesia",
  "Ataxia",
  "Achalasia",
  "Stiffness",
  "Side pain",
  "Unwell",
  "Anorexia",
  "Sensory discomfort",
  "Blackouts",
  "Withdraw",
  "Verbally abusive behavior",
  "Drowsiness",
  "Giddy mood",
  "Homicidal thoughts",
  "Decompensation",
  "Terrify",
  "Impaired cognition",
  "Stuffy nose",
  "Heavy legs",
  "Excessive urination",
  "Excessive thirst",
  "Vertigo",
  "Dullness",
  "Red blotches",
  "Painful urination",
  "Urgency of micturition",
  "Fecaluria",
  "Projectile vomiting",
  "Pneumatouria",
  "Cystic lesion",
  "Heberden's node",
  "Pericardial friction rub",
  "Hematocrit decreased",
  "Neck stiffness",
  "Behavior showing increased motor activity",
  "Scar tissue",
  "Coordination abnormal",
  "Myalgia",
  "Clammy skin",
  "Room spinning",
  "Cachexia",
  "Choke",
  "Uncoordination",
  "Absences finding",
  "Posturing",
  "Aura",
  "Tonic seizures",
  "Debilitation",
  "Hypometabolism",
  "Hemiplegia",
  "Myoclonus",
  "Gurgle",
  "Macule",
  "Para 2",
  "Abortion",
  "Intermenstrual heavy bleeding",
  "Previous pregnancies 2",
  "Primigravida",
  "Proteinemia",
  "Breath-holding spell",
  "Scleral icterus",
  "Retch",
  "Hot flush",
  "Emphysematous change",
  "Paresis",
  "Focal seizures",
  "Abnormal sensation",
  "Stupor",
  "Stahli's line",
  "Stinging sensation",
  "Bowel sounds decreased",
  "Hunger",
  "Burning sensation",
  "Numbness of hand",
  "Inappropriate affect",
  "Poor feeding",
  "Ache",
  "Macerated skin",
  "Heavy feeling",
  "Hyperventilation",
  "Excruciating pain",
  "Gag",
  "Food intolerance",
  "Pulse absent",
  "Fatigability",
  "Prodrome",
  "Cyanosis",
  "Para 1",
  "Feeling strange",
  "Mood depressed",
  "Estrogen use",
  "Photopsia",
  "Abdomen acute",
  "Air fluid level",
  "Catching breath",
  "Paraparesis",
  "Moody",
  "Fear of falling",
  "Nasal flaring",
  "Lip smacking",
  "Sneeze",
  "Snore",
  "Asymptomatic",
  "Milky",
  "Nervousness",
  "Regurgitates after swallowing",
  "Vision blurred",
  "Urinary hesitation",
  "Hypocalcemia result",
  "Hypothermia, natural",
  "Welt",
  "Tinnitus",
  "Hydropneumothorax",
  "Superimposition",
  "Mass in breast",
  "Retropulsion",
  "Formication",
  "Urinating blood",
  "Dysesthesia",
  "Polymyalgia",
  "Passed stones",
  "Hemianopsia homonymous",
  "Dizzy spells",
  "Shooting pain",
  "Systolic ejection murmur",
  "Hyperemesis",
  "Pulsus paradoxus",
  "Gravida 10",
  "Blanch",
  "Elation",
  "Ambidexterity",
  "Anosmia",
  "Pain neck",
  "Atypia",
  "Rhd positive",
  "Out of breath",
  "Sedentary",
  "Feels hot/feverish",
  "Hirsutism",
  "Sniffle",
  "Bradykinesia",
  "Stool color yellow",
  "Rigor - temperature-associated observation",
  "Hypoproteinemia",
  "Rest pain",
  "Transsexual",
  "Dyspareunia",
  "Hypokalemia",
  "Floppy",
  "Mediastinal shift",
  "Hepatomegaly",
  "General unsteadiness",
  "Bruit",
  "Hypersomnolence",
  "Underweight",
  "Breakthrough pain",
  "No known drug allergies",
  "Sciatica",
  "Frothy sputum",
  "Unable to concentrate",
  "Todd paralysis",
  "Alcoholic withdrawal symptoms",
  "Alcohol binge episode",
  "Monocytosis",
  "Posterior rhinorrhea",
  "Hypertonicity",
  "Phonophobia",
  "Rolling of eyes",
  "Rambling speech",
  "Clumsiness",
  "Flare",
  "Disequilibrium",
  "Throbbing sensation quality",
  "Pansystolic murmur",
  "Incoherent",
  "Lameness",
  "R wave feature",
  "Overweight",
  "Prostate tender",
  "Pain foot",
  "Soft tissue swelling",
  "Slowing of urinary stream",
  "No status change",
  "Barking cough",
  "Rapid shallow breathing",
  "Noisy respiration",
  "Nasal discharge present"
 };

 public static void FrameOperations(JFrame f, Color c, int length, int breadth) {

  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  f.getContentPane().setForeground(c);
  f.setSize(length, breadth);
  f.setVisible(true);

 }
 public static void PanelOperations(JPanel p, JFrame f, Color c, Color cAlt, int length, int breadth, int grid1, int grid2) {

  p.setLayout(new GridLayout(grid1, grid2));
  p.setBackground(c);
  p.setForeground(cAlt);
  p.setSize(length, breadth);
  f.getContentPane().add(p);
  f.setVisible(true);
 }

 public static void FrameVisibility(boolean b, JFrame f) {
  f.setVisible(b);
 }

 public static int[] backEnd(String symptoms[]) throws IOException {
  file = new FileInputStream(new File("Disease_Database.xlsx"));
  workbook = new XSSFWorkbook(file);
  XSSFSheet sheet = workbook.getSheetAt(0);
  int length = sheet.getLastRowNum() + 1;
  disease = new String[length];
  int weightage[] = new int[length];
  int test = 1;
  for (int i = 0; i < length; i++) {
   weightage[i] = 0;
   disease[i] = sheet.getRow(i).getCell(0).getStringCellValue();
  }
  for (int i = 0; i < 10; i++) {
   try {
    int index = checkSymptom(symptoms[i], 1);

    for (int j = 1; j <= 29; j++) {
     test++;
     weightage[checkSymptom(workbook.getSheetAt(1).getRow(index).getCell(j).getStringCellValue(), 0)]++;
    }
   } catch (Exception e) {

   }
  }

  int bestIndex[] = {
   0,
   1,
   2,
   3,
   4,
   5
  };
  int max = 0, index;
  for (int j = 0; j < 5; j++) {
   max = weightage[0];
   index = 0;
   for (int i = 1; i < weightage.length; i++) {
    if (max < weightage[i]) {
     max = weightage[i];

     index = i;
    }
    bestIndex[j] = index;
    weightage[index] = Integer.MIN_VALUE;
   }
  }

  int n = bestIndex.length;
  int temp = 0;
  for (int i = 0; i < n; i++) {
   for (int j = 1; j < (n - i); j++) {
    if (weightage[bestIndex[j - 1]] > weightage[bestIndex[j]]) {
     //swap elements  
     temp = bestIndex[j - 1];
     bestIndex[j - 1] = bestIndex[j];
     bestIndex[j] = temp;
    }

   }
  }

  workbook.close();
  return bestIndex;
 }
 public static int checkSymptom(String symptom, int ch) throws Exception {
  //ch=1 for symptom to disease ch=0 for disease to symptom


  file = new FileInputStream(new File("Disease_Database.xlsx"));


  workbook = new XSSFWorkbook(file);

  XSSFSheet sheet = workbook.getSheetAt(ch);

  Row row;
  int i;
  for (i = 1; i <= sheet.getLastRowNum(); i++) {
   row = sheet.getRow(i);

   Cell c1 = row.getCell(0);
   if (c1.getStringCellValue().equalsIgnoreCase(symptom))
    break;
  }
  workbook.close();
  return i;
 }

 public static void main(String args[]) throws IOException {

  UI obj = new UI();
  FrameOperations(f1, w, 600, 600);
  FrameVisibility(false, f1);
  PanelOperations(p1, f1, w, red_pigment, 600, 600, 12, 3);

  FrameVisibility(false, f1);
  ImageIcon logo = new ImageIcon("logo.png");
  JLabel jLabelObject = new JLabel();
  jLabelObject.setIcon(logo);

  p1.add(jLabelObject);
  JLabel l1 = new JLabel("COMMUCARE");
  l1.setForeground(green_munsell);
  l1.setFont(font);
  p1.add(l1);
  JLabel l5 = new JLabel("Our SHARED Healthcare Platform");
  p1.add(l5);
  p1.add(l8);
  dropDownOne = new JComboBox(s);
  dropDownOne.setBackground(w);
  dropDownOne.setForeground(green_munsell);
  AutoCompleteDecorator.decorate(dropDownOne);
  dropDownOne.setSize(150, 200);
  p1.add(dropDownOne);
  l3.setText("Chosen Symptoms - ");
  p1.add(l3);
  l10.setText("<html><body>Once, you click the 'Diagnose! button, <br> you will recieve a list of five probable diseases along with treatments<br>You can only select upto 10 symptoms</body></html>");
  p1.add(l10);
  p1.add(b);
  b.addActionListener(obj);
  dropDownOne.addItemListener(obj);
  FrameVisibility(true, f1);
  System.out.println(s.length);
 }





 public void itemStateChanged(ItemEvent e) {
  if (e.getSource() == dropDownOne) {

   i++;
   if (i < 20) {
    if (i % 2 == 0) {

     String l3_text = l3.getText();
     boolean repeat = false;
     for (int j = 0; j < 10; j++) {
      if (symptoms[j] == null) {
       break;
      } else {
       if (symptoms[j].compareTo((String) dropDownOne.getSelectedItem()) == 0) {
        repeat = true;
        i -= 2;
        break;
       } else {
        repeat = false;
        continue;
       }
      }
     }

     if (repeat == false) {
      l3.setText(l3_text + (dropDownOne.getSelectedItem()) + ", ");
      symptoms[i / 2] = (String) dropDownOne.getSelectedItem();
      FrameVisibility(true, f1);
     }
    }
   }
  }
 }

 public static String[] output(String disease) throws Exception {
  file = new FileInputStream(new File("Disease_Database.xlsx"));


  workbook = new XSSFWorkbook(file);
  XSSFSheet sheet1 = workbook.getSheetAt(0);
  XSSFSheet sheet3 = workbook.getSheetAt(2);
  String result[] = {
   "",
   ""
  };
  //result[0] stores symptomes result [1] stores the cure

  int symptomIndex = checkSymptom(disease, 0);

  for (int i = 1; i < 29; i++) {
   if (sheet1.getRow(symptomIndex).getCell(i).getStringCellValue().equals("**")) {

   } else {
    result[0] += sheet1.getRow(symptomIndex).getCell(i).getStringCellValue() + ", ";
   }
  }
  result[1] = sheet3.getRow(checkSymptom(disease, 2)).getCell(1).getStringCellValue();
  return result;


 }

 //static Font font = new Font("SanSerif", Font.BOLD, 48);

 public void actionPerformed(ActionEvent e) {
  if (e.getSource() == b) {
   try {
    JFrame f2 = new JFrame("Results - ");
    FrameOperations(f2, w, 1200, 750);
    FrameVisibility(false, f2);
    JPanel p3 = new JPanel();
    PanelOperations(p3, f2, green_munsell, w, 600, 600, 15, 1);
    int positions[] = backEnd(symptoms);
    l9.setFont(font);
    l9.setForeground(red_pigment);
    p3.add(l9);
    for (int j = 0; j < 5; j++) {
     Font font2 = new Font("SanSerif", Font.BOLD, 15);
     String ailment = disease[positions[j]];
     ailment = ailment.toUpperCase();
     System.out.println(ailment);
     String sympandtreat[] = output(ailment);
     JLabel ling = new JLabel("----------------------------------------------------------------------------------");
     JLabel l4 = new JLabel(ailment);
     
     l4.setFont(font);
     l4.setForeground(w);
     JLabel l6 = new JLabel("All Symptoms - " + sympandtreat[0]);

     System.out.println("All Symptoms - " + sympandtreat[0]);
     l6.setForeground(w);
     JLabel l7 = new JLabel("Suggested Treatment - " + sympandtreat[1]);
     l7.setFont(font2);
     l7.setForeground(w);
    
     p3.add(l4);

     p3.add(l7);

     FrameVisibility(true, f2);
    }
   } catch (IOException e1) {
    System.err.println(e1);

   } catch (Exception e1) {
    System.err.println(e1);
   }

  }

 }

}