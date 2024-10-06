package com.allabo.fyl.util;

import com.allabo.fyl.entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    // 카드 체크 파일 파싱
    public List<CardCheck> parseCardCheckCsv(String fileName) {
        List<CardCheck> cardChecks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String header = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    CardCheck cardCheck = new CardCheck(values[0], values[1], values[2]);
                    cardChecks.add(cardCheck);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cardChecks;
    }

    // 카드 크레딧 파일 파싱
    public List<CardCredit> parseCardCreditCsv(String fileName) {
        List<CardCredit> cardCredits = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String header = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    CardCredit cardCredit = new CardCredit(values[0], values[1], values[2]);
                    cardCredits.add(cardCredit);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cardCredits;
    }

    // 예금 파일 파싱
    public List<Deposits> parseDepositCsv(String fileName) {
        List<Deposits> deposits = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String header = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    Deposits deposit = new Deposits(values[0], values[1], values[2]);
                    deposits.add(deposit);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deposits;
    }

    // 대출 파일 파싱
    public List<Loan> parseLoanCsv(String fileName) {
        List<Loan> loans = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String header = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    Loan loan = new Loan(values[0], values[1], values[2]);
                    loans.add(loan);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loans;
    }

    // 보험 파일 파싱 (kb라이프, kb라이프2, kb손해보험)
    public List<Insurance> parseInsuranceCsv(String fileName) {
        List<Insurance> insurances = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String header = br.readLine();  // 첫 줄이 헤더라면 건너뜁니다.
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // 파일의 컬럼이 3개라고 가정 (insuranceName, coverage, premium)
                if (values.length == 3) {
                    Insurance insurance = new Insurance(values[0], values[1], values[2]);
                    insurances.add(insurance);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return insurances;
    }
}