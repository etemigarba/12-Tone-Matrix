import  java.util.Vector;
import 	java.util.Hashtable;
import 	java.util.Enumeration;
import 	java.util.*;
import 	java.util.List.*;
import 	java.util.ArrayList;
import 	java.util.Arrays;


public class TwelveToneMatrix{

public Object chromaticPitches[] = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
public Object originalRow[] = {"D","C#","A","A#","F","D#","E","C","G#","G","F#","B"};

public TwelveToneMatrix(){

getTwelveToneMatrix(getPichClassOriginalRow(chromaticPitches,originalRow));

displayTwelveToneMatrix(getTwelveToneMatrix(getPichClassOriginalRow(chromaticPitches,originalRow)));

}//end constructor...

//get Pitch Class of the Original 12 Tone Row Method...
public Object[] getPichClassOriginalRow(Object[] chromaticPitches, Object[] originalRow){
Object[] pitchClass = new Object[originalRow.length];

for(byte i=0; i<originalRow.length; i++){
for(byte j=0; j<chromaticPitches.length; j++){
if(originalRow[i]==chromaticPitches[j]){
pitchClass [i]=j;
break;
}//end if...

}//end for j...

}//end for i...

//Vector <Object> tempVector = new Vector<Object>();
//tempVector.copyInto(pitchClass);
//System.out.println(tempVector+" ");

return pitchClass;
}//end method getPichClassOriginalRow...


//get the 12 tone matrix method....
public Object[][] getTwelveToneMatrix(Object[] getPichClassOriginalRow){
Object[][] matrix = new Object[getPichClassOriginalRow.length][getPichClassOriginalRow.length];

//creating the first row of the Matrix from the Pich Class of the Original Row
for(byte j=0; j<getPichClassOriginalRow.length; j++){
matrix[0][j] = getPichClassOriginalRow[j];
}//end for j...

System.out.println(" ");
//creating the first column of the Matrix which is the Inverted Pich Class of the Original Row
for(byte i=1; i<getPichClassOriginalRow.length; i++){
int pitchClassValue = 12 - Integer.parseInt(matrix[0][i].toString());
if (pitchClassValue >= 12){
matrix[i][0] = pitchClassValue - 12;
}else{
matrix[i][0] = pitchClassValue;
}//end else...

}//end for i...


for(byte i=1; i<getPichClassOriginalRow.length; i++){
for(byte j=1; j<getPichClassOriginalRow.length; j++){

int pitchClassValue = Integer.parseInt(matrix[i][0].toString()) +Integer.parseInt(matrix[0][j].toString());
if (pitchClassValue >= 12){
matrix[i][j] = pitchClassValue - 12;
}else{
matrix[i][j] = pitchClassValue;
}//end else...
}//end for j...


}//end for i...

//System.out.println(matrix);
return matrix;
}//end method getTwelveToneMatrix...

//method for displaying the Pitch Class and Pitch Notes of the Twelve Tone Matrix
public void displayTwelveToneMatrix(Object [][] getTwelveToneMatrix){
Vector<Object> pitchClassVector = new Vector<Object>();
Vector<Object> pitchNotesVector = new Vector<Object>();

for(byte i=0; i<getTwelveToneMatrix.length; i++){
Vector<Object> pcRowVector = new Vector<Object>();
Vector<Object> pnRowVector = new Vector<Object>();


for(byte j=0; j<getTwelveToneMatrix.length; j++){

pcRowVector.add(getTwelveToneMatrix[i][j]);
//System.out.print(getTwelveToneMatrix[i][j]+" ");

//Display the Pitch Notes of the 12-Tone Matrix
for(byte k=0; k<chromaticPitches.length; k++){
if((int)k==Integer.parseInt(getTwelveToneMatrix[i][j].toString())){
//System.out.print(chromaticPitches[k]+" ");
break;
}//end if...

}//end k...

}//end j...
System.out.println(" ");
pitchClassVector.add(pcRowVector);

}//end i...


System.out.println(pitchClassVector+" ");

}//displayTwelveToneMatrix

public static void main(String args[]){
new TwelveToneMatrix();
}//end main...



}//end class TwelveToneMatrix...