/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlpmusic;

import java.util.ArrayList;
import weka.clusterers.Canopy;
import weka.clusterers.Clusterer;
import weka.clusterers.Cobweb;
import weka.clusterers.EM;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.clusterers.DBSCAN;

/**
 *
 * @author toshiba
 */
public class StringClusterer {
    
    double threshold = 1;
    int min_points = 6;
    public static void main(String[] args) throws Exception {
        MusicDataLoader load = new MusicDataLoader("music.json");
        ArrayList<musicdata> arr = load.load();   
        musicdata.removeNoise(arr);
        ArrayList<String> tem = musicdata.convert(arr, true);
        StringClusterer clust = new StringClusterer(1, 2);
        ArrayList<ArrayList<String>> ret = clust.cluster(tem);
        
        for (ArrayList<String> ar : ret) {
            //System.out.println("####");
            ArtistData ard = new ArtistData(ar);
            //System.out.println(ard.toString());
        }
        
    }
    
    public StringClusterer(double threshold, int min_points) {
        this.threshold = threshold;
        this.min_points = min_points;
    }
    
    public ArrayList<ArrayList<String>> cluster(ArrayList<String> tem) throws Exception {
        Instances source = listLoad(tem);        
        
        StringToWordVector vect = new StringToWordVector();
        vect.setInputFormat(source);
        Instances datas = Filter.useFilter(source, vect);
        
        DBSCAN clusterer = new DBSCAN();
        clusterer.setEpsilon(threshold);
        clusterer.setMinPoints(min_points);
        
        clusterer.buildClusterer(datas);        
        
        ArrayList<ArrayList<String>> ret = new ArrayList<>();
        
        for (int i = 0; i < clusterer.numberOfClusters(); i++) {
            ArrayList<String> to_add = new ArrayList<>();
            //System.out.println(i);
            for (int j = 0; j < datas.size(); j++) {                           
                try {
                    if (clusterer.clusterInstance(datas.get(j)) == i)
                        //System.out.println("* " + source.get(j).toString() + " *");
                        to_add.add(source.get(j).toString());
                } catch (Exception e) {
                    //e.printStackTrace();
                }                
            }
            ret.add(to_add);
        }
        return ret;
    }        
        
    
    public static Instances listLoad(ArrayList<String> list) {        
        FastVector attributes = new FastVector();
        attributes.addElement(new Attribute("attr", (FastVector) null));
        Instances datas = new Instances("Strings", attributes,0);
        
        for (String str : list) {
            DenseInstance inst = new DenseInstance(1);
            inst.setValue(datas.attribute(0), str);            
            datas.add(inst);
        }
        return datas;
    }
}
