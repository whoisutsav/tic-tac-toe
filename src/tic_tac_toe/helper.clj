(ns tic-tac-toe.helper
  (:require  [clojure.math.numeric-tower :as math]))

(defn axis-size [board]
  (math/sqrt (count board)))

(defn prompt [question]
  (println question)
  (read-line))


