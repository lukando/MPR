# System Zarządzania Pracownikami (Integracja CSV i API)

Prosty projekt w Javie demonstrujący import danych z CSV, pobieranie danych z REST API oraz podstawowe operacje analityczne na danych.

## Wymagania

* Java JDK 11 lub nowsza (ze względu na `HttpClient`)
* Apache Maven (do zarządzania zależnościami)

## Zależności

* `com.google.code.gson` (do parsowania JSON z API)

## Struktura Projektu

src/
├── model/
│   ├── Employee.java
│   ├── Position.java
│   ├── ImportSummary.java
│   └── CompanyStatistics.java
├── service/
│   ├── EmployeeService.java
│   ├── ImportService.java
│   └── ApiService.java
├── exception/
│   ├── InvalidDataException.java
│   └── ApiException.java
└── Main.java

## Działanie

Program po uruchomieniu:
1.  Zaimportuje dane z pliku `employees.csv`, wypisując podsumowanie i ewentualne błędy.
2.  Pobierze dane użytkowników z publicznego API `jsonplaceholder.typicode.com`.
3.  Doda pobranych użytkowników do serwisu (o ile ich maile nie kolidują z danymi z CSV).
4.  Wyświetli pełną listę wszystkich pracowników w systemie.
5.  Wyświetli listę pracowników, których pensja jest niższa niż bazowa dla ich stanowiska.
6.  Wyświetli statystyki (liczbę pracowników, średnią pensję, najlepiej zarabiającego) dla każdej firmy.
