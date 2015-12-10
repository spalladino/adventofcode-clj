(ns adventofcode.core
  (:require [adventofcode.day01 :as day01])
  (:require [adventofcode.day02 :as day02])
  (:require [adventofcode.day03 :as day03])
  (:require [adventofcode.day04 :as day04])
  (:require [adventofcode.day05 :as day05])
  (:require [adventofcode.day06 :as day06])
  (:require [adventofcode.day07 :as day07]))

(def days
  {1 day01/main
   2 day02/main
   3 day03/main
   4 day04/main
   5 day05/main
   6 day06/main
   7 day07/main})

(defn -main [arg]
  (let [day (Integer/parseInt arg)]
    (println (str "Day " day))
    ((days day))))
