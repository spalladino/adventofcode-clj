(ns adventofcode.day23
  (:require [clojure.string :as str]))

(defn instr-hlf [state reg]
  (-> state
    (update reg / 2)
    (update :pc inc)))

(defn instr-tpl [state reg]
  (-> state
    (update reg * 3)
    (update :pc inc)))

(defn instr-inc [state reg]
  (-> state
    (update reg inc)
    (update :pc inc)))

(defn instr-jmp [state offset]
  (-> state
    (update :pc + offset)))

(defn instr-jie [state reg offset]
  (if (even? (state reg))
    (update state :pc + offset)
    (update state :pc inc)))

(defn instr-jio [state reg offset]
  (if (= 1 (state reg))
    (update state :pc + offset)
    (update state :pc inc)))

(defn parse-line [line]
  (let [[match cmd reg-str offset-str] (re-find #"(hlf|tpl|inc|jmp|jie|jio)\s*(a|b)?\s*,?\s*((?:\+|-)\d+)?" line)
        reg (if reg-str (keyword reg-str))
        offset (if offset-str (Integer/parseInt offset-str))
        args (remove nil? [reg offset])]
    `(~(ns-resolve 'adventofcode.day23 (symbol (str "instr-" cmd))) ~@args)))

(defn parse-data [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map parse-line)))

(defn run [instructions initial-state]
  (loop [state initial-state]
    (if-let [[cmd & args] (nth instructions (:pc state) nil)]
      (recur (eval `(~cmd ~state ~@args)))
      state)))

(defn main []
  (let [instructions (parse-data (slurp "./inputs/day23.txt"))]
    (println "With a=0: " (:b (run instructions { :pc 0, :a 0, :b 0 })))
    (println "With a=1: " (:b (run instructions { :pc 0, :a 1, :b 0 })))))
