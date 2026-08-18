import  java.util.Vector;
import 	java.util.Hashtable;
import 	java.util.Enumeration;
import 	java.util.*;
import 	java.util.List.*;
import 	java.util.ArrayList;
import 	java.util.Arrays;


public class TwelveToneMatrix{



public TwelveToneMatrix(){

System.out.println(chromaticPitches());
System.out.println(originalRow());
System.out.println(getPichClassOriginalRow(chromaticPitches(),originalRow()));
System.out.println(getTwelveToneMatrix(getPichClassOriginalRow(chromaticPitches(),originalRow())));

}//end constructor...


//chromaticPitches Method...
public Vector<Object> chromaticPitches(){
Object pitchesArray [] = {"A","A#","B","C","C#","D","D#","E","F","F#","G","G#"};
Vector<Object> pitches = new Vector<Object>(Arrays.asList(pitchesArray));
return pitches;
}//end method chromaticPitches...

//Original 12 Tone Row Method...
public Vector<Object> originalRow(){
Object pitchesArray [] = {"D","C#","A","A#","F","D#","E","C","G#","G","F#","B"};
Vector<Object> pitches = new Vector<Object>(Arrays.asList(pitchesArray));
return pitches;
}//end method originalRow...

//get Pitch Class of the Original 12 Tone Row Method...
public Vector<Object> getPichClassOriginalRow(Vector<Object> chromaticPitches, Vector<Object> originalRow){
Vector<Object> pitchClass = new Vector<Object>(originalRow.size());

for(byte i=0; i<originalRow.size(); i++){
for(byte j=0; j<chromaticPitches.size(); j++){
if(originalRow.get(i)==chromaticPitches.get(j)){
pitchClass.add(i,j);
break;
}//end if...

}//end for j...

}//end for i...

return pitchClass;
}//end method getPichClassOriginalRow...


//get the 12 tone matrix method....
public Vector<Object> getTwelveToneMatrix(Vector<Object> getPichClassOriginalRow){
Vector<Object> matrix = new Vector<Object>(getPichClassOriginalRow.size());
Vector<Object> rowVector;

for(byte i=0; i<getPichClassOriginalRow.size(); i++){
for(byte j=0; j<getPichClassOriginalRow.size(); j++){
if(i==0){
//rowVector = new Vector<Object>();
matrix.add(0,getPichClassOriginalRow);

//pitchClass.add(i,j);
//break;
}//end if...

}//end for j...

}//end for i...

System.out.println(matrix);
return matrix;
}//end method getTwelveToneMatrix...



public static void main(String args[]){

new TwelveToneMatrix();


}//end main...





}//end class TwelveToneMatrix...