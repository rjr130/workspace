///////////////////////////////////////////////////////////////////////////////
//
// Main Class File:  NaiveBayesClassifierImpl.java
// File:             NaiveBayesClassifierImpl.java
// Semester:         Fall 2012
//
// Author:     		 Junrui Ruan jruan@wisc.edu
// CS Login:         junrui
// Lecturer's Name:  Xiaojin Zhu
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          none
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.HashMap;
import java.util.Map;

/**
 * Your implementation of a naive bayes classifier. Please implement all four methods.
 */

public class NaiveBayesClassifierImpl implements NaiveBayesClassifier {
	/**
	 * Trains the classifier with the provided training data and vocabulary size
	 */
	// V size
	private double vocSize = 0;
	// Ham count
	private double clH = 0;
	// Spam count
	private double clS = 0;
	// the greek character delta representing 0.00001
	private double delta = 0.00001;
	// the spam hashmap
	HashMap<String, Integer> spamData = new HashMap<String, Integer>();
	// the ham hashmap
	HashMap<String, Integer> hamData = new HashMap<String, Integer>();
	// to count how many texts hold label ham
	private double h = 0;
	// to count how many texts hold label spam
	private double s = 0;
	
	@Override
	public void train(Instance[] trainingData, int v) {
		// Implement
		this.vocSize = v;
		// use hashmap to store the words and the tokens of the word
		// two different hashmaps, one for ham, one for spam
		for (int i = 0; i < trainingData.length; i++) {
			if (trainingData[i].label == Label.HAM) {
				h++;
			}
			else {
				s++;
			}
			for (int j = 0; j < trainingData[i].words.length; j++) {
				int tmp = 0;
				if (trainingData[i].label == Label.HAM) {
					if (hamData.get(trainingData[i].words[j]) == null) {
						hamData.put(trainingData[i].words[j], new Integer(1));
						//h++;
					}
					else {
						tmp = hamData.get(trainingData[i].words[j]);
						tmp++;
						hamData.put(trainingData[i].words[j], new Integer(tmp));
					}
					clH++;
				}
				else {
					if (spamData.get(trainingData[i].words[j]) == null) {
						spamData.put(trainingData[i].words[j], new Integer(1));
						//s++;
					}
					else {
						tmp = spamData.get(trainingData[i].words[j]);
						tmp++;
						spamData.put(trainingData[i].words[j], new Integer(tmp));
					}
					clS++;
				}
			}
		}
	}

	/**
	 * Returns the prior probability of the label parameter, i.e. P(SPAM) or P(HAM)
	 */
	@Override
	public double p_l(Label label) {
		// calculate the p(spam) & p(ham)
		if (label == Label.SPAM) {
			//return clS / (clS + clH);
			return s / (s + h);
		}
		else {
			//return clH / (clS + clH);
			return h / (s + h);
		}
	}

	/**
	 * Returns the smoothed conditional probability of the word given the label,
	 * i.e. P(word|SPAM) or P(word|HAM)
	 */
	@Override
	public double p_w_given_l(String word, Label label) {
		// cl(w) (could avoid the case that word not existed in the training
		// data set)
		int clw = 0;
		// cl(v)
		double clv = 0;
		// p(w|l)
		double p_w = 0;
		
		if (label == Label.SPAM) {
			if (spamData.get(word) != null) {
				clw = spamData.get(word);
			}
		}
		else {
			if (hamData.get(word) != null) {
				clw = hamData.get(word);
			}
		}
		
		if (label == Label.HAM) {
			clv = clH;
		}
		else {
			clv = clS;
		}
		
		p_w = (clw + this.delta) / ((this.vocSize * this.delta) + clv);
		
		return p_w;
	}
	
	/**
	 * Classifies an array of words as either SPAM or HAM. 
	 */
	@Override
	public ClassifyResult classify(String[] words) {
		// Implement
		ClassifyResult result = new ClassifyResult();
		
		// Calculating
		double p_w_S = 0;
		double p_w_H = 0;
		
		for (int i = 0; i < words.length; i++) {
			p_w_S += Math.log(p_w_given_l(words[i], Label.SPAM));
			p_w_H += Math.log(p_w_given_l(words[i], Label.HAM));
		}
		
		result.log_prob_ham = Math.log(p_l(Label.HAM)) + p_w_H;
		result.log_prob_spam = Math.log(p_l(Label.SPAM)) + p_w_S;
		
		if (result.log_prob_ham >= result.log_prob_spam) {
			result.label = Label.HAM;
		}
		else {
			result.label = Label.SPAM;
		}
		return result;
	}
}
