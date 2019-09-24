package br.ufu.murilo.model;

import java.util.*;

public class KNearestNeighbor {
    // K is the number of nearest neighbors
    int K;
    boolean normalize;
    float attributesAverage[];
    private int numberOfElements;
    private int numberOfAttributes;

    private List<Element> dataSet;

    private List<Element> foldDataSet;
    private boolean useFoldDataSet = false;

    public KNearestNeighbor(int K, int numberOfElements, int numberOfAttribute, boolean normalize) {
        this.K = K;
        this.numberOfElements = numberOfElements;
        this.numberOfAttributes = numberOfAttribute;
        this.normalize = normalize;
    }

    private void calculateElementEuclideanDistanceFromUnknow(Element element, Element unknownObject){
        double sum = 0;
        for(int i = 0; i < numberOfAttributes; i++){
            double aux = Math.abs(element.getAttribute(i) - unknownObject.getAttribute(i));
            sum += aux * aux;
        }
        element.setDistanceFromUnknowObject(Math.sqrt(sum));
    }


    public void classifyUnknownObject(Element unkownObject, boolean useFoldDataSet){
        this.useFoldDataSet = useFoldDataSet;
        classifyUnknownObject(unkownObject);
        this.useFoldDataSet = false;
    }
    public void classifyUnknownObject(Element unkownObject){
        calculateAllElementsEuclideanDistanceFromUnknow(unkownObject);
        debugDataSet();
    }

    private void debugDataSet() {
        for(Element e : getDataSet()){
            System.out.println(e.getDistanceFromUnknowObject());
        }
    }

    private void calculateAllElementsEuclideanDistanceFromUnknow(Element unknownObject){
        for(Element element : getDataSet()){
            calculateElementEuclideanDistanceFromUnknow(element , unknownObject);
        }
    }

    private void normalizeDataSet(){
        attributesAverage = new float[numberOfAttributes];

        //average
        for(Element element : getDataSet()){
            for(int i = 0; i < numberOfAttributes; i++){
                attributesAverage[i] += element.getAttribute(i);
            }
        }
        for(int i = 0; i < numberOfAttributes; i++){
            attributesAverage[i] = attributesAverage[i]/(float)getDataSet().size();
        }


        //changing element values to the normalized ones
        for(Element element : getDataSet()){
            for(int i = 0; i < numberOfAttributes; i++){
                element.setAttribute(i, (element.getAttribute(i))/ attributesAverage[i]); //attributesStdDeviation[i]
            }
        }
    }

    public void setDataSetAndNormalize(List<Element> dataSet, boolean normalize) {
        this.dataSet = dataSet;

        if(normalize){
            normalizeDataSet();
        }
    }

    public List<Element> getDataSet() {
        if(useFoldDataSet){
            return foldDataSet;
        } else {
            return dataSet;
        }
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }


    public void setFoldDataSet(List<Element> foldDataSet) {
        this.foldDataSet = foldDataSet;
    }
}
