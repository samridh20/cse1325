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
        std::string accountName;
        std::cout << "Name account " << i << ": ";
        std::getline(std::cin, accountName);

        Purse purse;
        std::cout << "Enter your initial deposit (£ pounds shillingsd): ";
        std::cin >> purse;
        vault[accountName] = purse;
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