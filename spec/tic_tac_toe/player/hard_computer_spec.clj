(ns tic-tac-toe.player.hard-computer-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.player.hard-computer :refer :all]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.board :as board]))

(declare can-i-win?)

(defn can-i-win-my-move? [board my-marker computer-marker]
  (->> (possible-boards board my-marker) 
           (map #(can-i-win? % my-marker computer-marker false))
           (reduce #(or %1 %2))))

(defn can-i-win-computer-move? [board my-marker computer-marker]
  (let [computer-move (get-move board {:marker computer-marker :type :hard-computer} {:marker my-marker})
       updated-board (board/put-marker board computer-marker computer-move)]
    (can-i-win? updated-board my-marker computer-marker true)))

;TODO pass in my-player/computer-player instead of my-marker/computer-marker
(defn can-i-win? [board my-marker computer-marker is-my-turn]
  (if (decision/over? board)
    (= my-marker (decision/winner board))
    (if is-my-turn
      (can-i-win-my-move? board my-marker computer-marker)      
      (can-i-win-computer-move? board my-marker computer-marker)))) 

(describe "hard-computer"
          (around [it] (with-out-str (it)))
          (it "is unbeatable against all possible states"
              (should= false (can-i-win? (board/new-board 3) :X :O true))))


(describe "hard-computer"
          (it "chooses the best space"
              (should= 9 (get-move [ :_ :x :_
                                     :o :o :x
                                     :x :x :_ ] {:marker :o :type :hard-computer} {:marker :x :type :human}))))
