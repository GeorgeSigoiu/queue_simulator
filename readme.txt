Descriere
	Obiectivul principal pentru acest proiect a fost lucrul cu thread-uri.
	Aplicația simulează comportamentul clienților în magazin. Inițial sunt generați N clienți, care au un timp de sosire (timpul în care caută în magazin, apoi se așază la o coadă de așteptare pentru a plăti produsele achiziționate) și un timp de servire (timpul cât durează să plătească ce a cumpărat) și Q cozi de așteptare (fiecare coadă de așteptare este reprezentată printr-un thread). Clienții se plimbă prin magazin, iar după ce le trece timpul de sosire se așază la coadă.  Coada la care se așază clientul respectiv este aleasă în funcție de strategia aleasă: coada cu cel mai mic timp de așteptare sau coada cu cei mai puțini clienți. 

Utilizare
	Aplicația se pornește rulând run.exe.
	Prima interfață care va apărea va fi cea de control, în care utilizatorul setează datele pentru simulare. După introducerea datelor de simularea și selectarea strategiei (Selection policy) se apasă butonul start pentru pornirea simulării iar altă interfață va apărea. Atenție! Datele introduse trebuie să fie numere întregi pozitive!
	În cea de-a doua interfață grafică se pot observa cele două coloane în care apar cozile de așteptare, împreună cu clienții care așteaptă la coada respectivă. În panel-ul mare se pot observa clienții din magazin, pentru care este afișat timpul rămas până la așezarea la coadă și timpul necesar pentru a fi servit. În partea de sus este actualizat în fiecare secundă timpul care a trecut de la pornirea simulării și sunt afișate datele cu care a început simularea.
	
