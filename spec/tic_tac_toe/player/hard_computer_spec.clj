(ns tic-tac-toe.player.hard-computer-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.player.hard-computer :refer :all]))

(describe "hard-computer"
          (it "chooses the best space"
              (should= 9 (get-move [ :_ :x :_
                                     :o :o :x
                                     :x :x :_ ] {:marker :o :type :hard-computer}))))

;(describe "hard-computer"
;          (it "chooses the best space"
;              (should= 9 (get-move [ :o :x :_
;                                     :o :o :x
;                                     :x :x :_ ] {:marker :o :type :computer}))))

;(describe "hard-computer"
;          (it "chooses the best space (inverted)"
;              (should= 3 (get-move [ :x :x :_
;                                     :o :o :x
;                                     :o :x :_ ] {:marker :o :type :computer}))))

(describe "hard-computer"
          (it "calculates max loss"
              (should= 0 (max-loss :o :x :o [ :_ :x :_
                                              :o :o :x
                                              :x :x :_ ])))) 


(describe "hard-computer"
          (it "calculates max loss"
              (should= 0 (max-loss :x :o :x [ :_ :x :_
                                              :x :x :o
                                              :o :o :_ ]))))

(describe "other-marker"
          (it "gets other marker"
              (should= :x (get-other-marker [ :_ :x :_
                                              :o :o :x
                                              :x :x :_ ] :o))))
