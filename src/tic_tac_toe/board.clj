(ns tic-tac-toe.board) 

(def empty-space :_)

(defn size [board]
  (case (count board)
    4 2
    9 3
    16 4))

(defn new-board [size] 
  (vec (repeat (* size size) empty-space)))

(defn put-marker [board marker space]
  (assoc board (dec space) marker))

(defn get-marker [space board]
  (let [marker (nth board (dec space))]
    (if (= empty-space marker)
      nil
      marker)))

(defn get-empty-spaces [board]
  (let [board-size (size board)] 
    (->> (range 1 (inc (* board-size board-size)))
         (filterv #(nil? (get-marker % board))))))

