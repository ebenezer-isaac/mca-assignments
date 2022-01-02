void setup() {
  pinMode(9, OUTPUT);//RED
}
void loop() {

////dash
//  digitalWrite(9, HIGH);
//  delay(500); 
///blank
//  digitalWrite(9, LOW); 
//  delay(500); 
////dot
//  digitalWrite(9, HIGH);
//  delay(100); 
////difference
//  digitalWrite(9, LOW);
//  delay(100); 

// MORSE CODE : M C A

//m
 digitalWrite(9, HIGH);
 delay(1000); 
 digitalWrite(9, LOW);
 delay(500); 
 digitalWrite(9, HIGH);
 delay(1000); 
 //space
 digitalWrite(9, LOW); 
 delay(1000); 
 //c
 digitalWrite(9, HIGH);
 delay(1000);
 digitalWrite(9, LOW);
 delay(500); 
 digitalWrite(9, HIGH);
 delay(500); 
 digitalWrite(9, LOW);
 delay(500); 
 digitalWrite(9, HIGH);
 delay(1000);
 digitalWrite(9, LOW);
 delay(500); 
 digitalWrite(9, HIGH);
 delay(500);  
 //space
 digitalWrite(9, LOW); 
 delay(1000); 
 //a
 digitalWrite(9, HIGH);
 delay(500); 
 digitalWrite(9, LOW);
 delay(500); 
 digitalWrite(9, HIGH);
 delay(1000); 
 //off
 digitalWrite(9, LOW);
 delay(3000); 


}
