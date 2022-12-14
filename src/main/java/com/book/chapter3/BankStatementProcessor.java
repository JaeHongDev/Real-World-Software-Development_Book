package com.book.chapter3;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions){
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount(){
        double total = 0;
        for(final BankTransaction bankTransaction: bankTransactions){
            total += bankTransaction.getAmount();
        }
        return total ;
    }

    public double calculateTotalInMonth(final Month month){
        double total = 0;
        for(final BankTransaction bankTransaction: bankTransactions){
            if(bankTransaction.getDate().getMonth() == month){
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalForCategory(final String category){
        double total = 0;
        for(final BankTransaction bankTransaction: bankTransactions){
            if(bankTransaction.getDescription().equals(category)){
                total += bankTransaction.getAmount();
            }
        }
        return total;
    }



    // 특정 월의 입출금 내역 찾기
    public List<BankTransaction> findTransactionsInMonth(final Month month){
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction: bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    //특정 월이나 금액으로 입출금 내역 검색하기
    public List<BankTransaction> findTransactionsInMonthAndGreater(final Month month,final int amount){
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction:bankTransactions){
            if(bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() >= amount){
                result.add(bankTransaction);
            }
        }
        return result;
    }

    //개방/폐쇄 원칙을 적용한 후 유연해진 findTransactions()메서드
    public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter){
        final List<BankTransaction> result = new ArrayList<>();
        for(final BankTransaction bankTransaction:bankTransactions){
            if(bankTransactionFilter.test(bankTransaction)){
                result.add(bankTransaction);
            }
        }
        return result;
    }
    // 특정 금액 이상의 은행거래 내역 찾기

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount){
        return this.findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }
}
