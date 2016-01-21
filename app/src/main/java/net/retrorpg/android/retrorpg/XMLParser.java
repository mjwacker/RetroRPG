package net.retrorpg.android.retrorpg;

/**
 * Created by u2981 on 1/21/2016.
 */
public class XMLParser extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        /** Create a new layout to display the view */
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);

        /** Create a new textview array to display the results */
        TextView name[];
        TextView website[];
        TextView category[];

        try {

            //add the doc url
            URL url = new URL("http://www.androidpeople.com/wp-content/uploads/2010/06/example.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");

            /** Assign textview array lenght by arraylist size */
            name = new TextView[nodeList.getLength()];
            website = new TextView[nodeList.getLength()];
            category = new TextView[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                name[i] = new TextView(this);
                website[i] = new TextView(this);
                category[i] = new TextView(this);

                Element fstElmnt = (Element) node;
                NodeList nameList = fstElmnt.getElementsByTagName("name");
                Element nameElement = (Element) nameList.item(0);
                nameList = nameElement.getChildNodes();
                name[i].setText("Name = " + ((Node) nameList.item(0)).getNodeValue());

                NodeList websiteList = fstElmnt.getElementsByTagName("website");
                Element websiteElement = (Element) websiteList.item(0);
                websiteList = websiteElement.getChildNodes();
                website[i].setText("Website = " + ((Node) websiteList.item(0)).getNodeValue());

                category[i].setText("Website Category = " + websiteElement.getAttribute("category"));

                layout.addView(name[i]);
                layout.addView(website[i]);
                layout.addView(category[i]);
            }
        } catch (Exception e) {
            System.out.println("XML Pasing Excpetion = " + e);
        }

        /** Set the layout view to display */
        setContentView(layout);

    }

}
