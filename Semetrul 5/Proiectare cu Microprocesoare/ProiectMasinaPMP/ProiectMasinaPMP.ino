//Acest proiect simplu este folosit pentru o masina cu 2 motoare DC, punte H, senzor de distanta, cu un modulul bluetooth,
//fiind conectata la telefon, comenzile fiind date de la 5 butoane, fata, spate, dreapta,stanga si stop. Daca senzorul de distanta 
//simtea ca masina se izbeste el trimitea un semnal si masina se oprea, iar pentru o scurta perioada de timp mergea cu spatele


#include <Wire.h>
#include <SoftwareSerial.h>


#define ECHOPIN 2 // Pin to receive echo pulse
#define TRIGPIN 3 // Pin to send trigger pulse
int dist = 0;
int var = 0;
void setup() {
  Serial1.begin(9600);
  Serial.begin(9600);

  pinMode(12, OUTPUT);  //left motors reverse
  pinMode(11, OUTPUT);  //left motors forward
  pinMode(10, OUTPUT);  //right motors forward
  pinMode(9, OUTPUT);  //right motors reverse
  pinMode(ECHOPIN, INPUT);
  pinMode(TRIGPIN, OUTPUT);
}
void loop() {
  // Start Ranging
  digitalWrite(TRIGPIN, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIGPIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIGPIN, LOW);
  // Compute distance
  float distance = pulseIn(ECHOPIN, HIGH);
  distance = distance / 58;
  Serial.println(distance);
  //delay(200);
  if (Serial1.available()) // Citire de pe Bluetooth, trimite la PC
  {
    var = Serial1.read();
    Serial.println(var);

  }

  if (var == 49) { // merge in fata
    digitalWrite(10, HIGH);
    digitalWrite(11, HIGH);

  }

  if (var == 50) { // merge in spate
    digitalWrite(12, HIGH);
    digitalWrite(9, HIGH);

  }

  if (var == 51) { // merge in stanga
    digitalWrite(11, HIGH);
    digitalWrite(9, HIGH);

  }

  if (var == 52) { // merge in dr
    digitalWrite(12, HIGH);
    digitalWrite(10, HIGH);

  }
  if (var == 53) {
    digitalWrite(11, LOW);
    digitalWrite(9, LOW);
    digitalWrite(12, LOW);
    digitalWrite(10, LOW);
  }

  if (distance <= 20.00) {

    digitalWrite(11, LOW);
    digitalWrite(9, LOW);
    digitalWrite(12, LOW);
    digitalWrite(10, LOW);
    dist = 1;

  }
  if (dist == 1)
  {
    dist = 0;
    digitalWrite(12, HIGH);
    digitalWrite(9, HIGH);
    delay(100);
    var = 53;
  }

}
