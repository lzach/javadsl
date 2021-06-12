package patience;

import java.util.Objects;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import patience.Card;
import patience.Pile;
import patience.PileGroup;
import patience.Deck;
public class PatienceKlondike extends patience.PatGame {
PileGroup tableau = new PileGroup(7);
PileGroup foundation = new PileGroup(4);
PileGroup waste = new PileGroup(1);
;public boolean canMove(Card card,Pile pile) {
{
if (tableau.contains(pile)){
if (Objects.equals(pile.size(), 0)){
return Objects.equals(card.value(), 13);
}
 else {
return (Objects.equals(card.value(), (pile.top().value()-1))&&(card.color()!=pile.top().color()));
}
}
if (foundation.contains(pile)){
if (Objects.equals(pile.size(), 0)){
return Objects.equals(card.value(), 1);
}
 else {
return (Objects.equals(card.value(), (pile.top().value()+1))&&Objects.equals(card.suit(), pile.top().suit()));
}
}
if (waste.contains(pile)){
if (Objects.equals(pile.size(), 0)){
return false;
}
 else {
return false;
}
}
return false;
}

}

public boolean canMovePile(Pile from,Pile to) {
{
if (tableau.contains(to)){
if (Objects.equals(from.size(), 0)){
return Objects.equals(from.bottom().value(), 13);
}
 else {
return ((Objects.equals(from.bottom().value(), (to.top().value()-1))&&(from.bottom().color()!=to.top().color()))&&true);
}
}
return false;
}

}

public PileGroup getPileGroup(String name) {
{
if ("tableau".equals(name)){
return tableau;
}
if ("foundation".equals(name)){
return foundation;
}
if ("waste".equals(name)){
return waste;
}
return null;
}

}

public boolean shouldTurn(Pile pile,Card card) {
{
return (waste.contains(pile)||Objects.equals(pile.top(), card));
}

}

public boolean hasLost(Deck deck) {
{
return false;
}

}

public boolean hasWon() {
{
for (Pile pile : foundation){
if (!(Objects.equals(pile.top().value(), 13))){
return false;
}
 else {
}
}
return true;
}

}

public void redeal(Deck deck) {
{
for (Pile pile : waste){
deck.putBottom(pile);
}
}

}

public void setup(Deck deck) {
{
for (int i = 6;(i<=6);++(i)){
tableau.get(i).add(deck.deal());
}
for (int i = 5;(i<=6);++(i)){
tableau.get(i).add(deck.deal());
}
for (int i = 4;(i<=6);++(i)){
tableau.get(i).add(deck.deal());
}
for (int i = 3;(i<=6);++(i)){
tableau.get(i).add(deck.deal());
}
for (int i = 2;(i<=6);++(i)){
tableau.get(i).add(deck.deal());
}
for (int i = 1;(i<=6);++(i)){
tableau.get(i).add(deck.deal());
}
for (int i = 0;(i<=6);++(i)){
tableau.get(i).add(deck.deal());
}
}

}

public void deal(Deck deck) {
{
for (Pile pile : waste){
pile.add(deck.deal());
}
}

}
}
