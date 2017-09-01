(ns tic-tac-toe.board) 

(def empty-space :_)

(defn size [board]
  (count board))

(defn new-board [axis-size] 
  (vec (repeat (* axis-size axis-size) empty-space)))

(defn put-marker [board marker cell]
  (assoc board (dec cell) marker))

(defn get-marker [cell board]
  (let [marker (nth board (dec cell))]
    (if (= empty-space marker)
      nil
      marker)))

(defn get-empty-spaces [board]
  (->> (range 1 (inc (size board)))
       (filter #(nil? (get-marker % board)))
       (vec)))

