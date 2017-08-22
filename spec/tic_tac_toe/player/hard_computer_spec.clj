(ns tic-tac-toe.player.hard-computer-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.player.hard-computer :refer :all]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.board :as board]))

(defn can-i-win? [board my-marker computer-marker is-my-turn]
  (if (decision/over? board)
    (= my-marker (decision/winner board))
    (if is-my-turn
      (->> (possible-boards board my-marker) 
           (map #(can-i-win? % my-marker computer-marker false))
           (reduce #(or %1 %2)))
      (let [computer-move (get-move board {:marker computer-marker :type :hard-computer} {:marker my-marker})
            updated-board (board/put-marker board computer-move computer-marker)]
        (can-i-win? updated-board my-marker computer-marker true))))) 

(describe "hard-computer"
          (around [it] (with-out-str (it)))
          (it "is unbeatable against all possible states"
              (should= false (can-i-win? (board/new-board) :X :O true))))


;(describe "hard-computer"
;          (it "chooses the best space"
;              (should= 9 (get-move [ :_ :x :_
;                                     :o :o :x
;                                     :x :x :_ ] {:marker :o :type :hard-computer}))))
;
;
;(describe "hard-computer"
;          (it "calculates max loss"
;              (should= 0 (max-loss :o :x :o [ :_ :x :_
;                                              :o :o :x
;                                              :x :x :_ ])))) 
;
;
;(describe "hard-computer"
;          (it "calculates max loss"
;              (should= 0 (max-loss :x :o :x [ :_ :x :_
;                                              :x :x :o
;                                              :o :o :_ ]))))
;
;(describe "other-marker"
;          (it "gets other marker"
;              (should= :x (get-other-marker [ :_ :x :_
;                                              :o :o :x
;                                              :x :x :_ ] :o))))
