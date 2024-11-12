#include <iostream>
#include <string>
#include <map>
#include "Purse.h"

int main() {
    std::cout << "Welcome to Ye Olde Bank of Merry England" << std::endl;

    int accountCount;
    std::cout << "How many accounts? ";
    std::cin >> accountCount;

    std::map<std::string, Purse> vault;

    for (int i = 0; i < accountCount; ++i) {
        std::cin.ignore(); 

       
        std::string accountName;                             // Declare variables for account details
        int pounds, shillings, pence;

        std::cout << "Name account " << i << ": ";
        std::getline(std::cin, accountName);

        std::cout << "Enter your initial deposit (pounds shillings pence): ";
        std::cin >> pounds >> shillings >> pence;

        vault[accountName] = Purse(pounds, shillings, pence);
        std::cout << "Account " << accountName << " created with " << vault[accountName] << std::endl;
    }

    std::cout << "\nAccount List" << std::endl;
    std::cout << "============" << std::endl;

    Purse total;
    for (const auto& [name, purse] : vault) {
        std::cout << "  " << name << " with " << purse << std::endl;
        total += purse;
    }

    std::cout << "\nTotal in bank is " << total << std::endl;
    return 0;
}