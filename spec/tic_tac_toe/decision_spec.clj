(ns tic-tac-toe.decision-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.decision :refer :all]))

(describe "winner"
          (it "returns nil for no winner"
              (should= nil (winner [:_ :_ :_
                                    :_ :_ :_
                                    :_ :_ :_])))
          (it "returns player for winning row"
              (should= :x (winner [:x :x :x
                                   :_ :o :o
                                   :_ :_ :_ ])))
          (it "returns player for winning column"
              (should= :o (winner [:o :x :_
                                   :o :x :_
                                   :o :_ :x])))
          (it "returns player for winning diagonal"
              (should= :o (winner [:o :x :_
                                   :x :o :_
                                   :x :_ :o]))))

(describe "over?"
          (it "returns true if game is won"
              (should= true (over? [:x :x :x
                                    :_ :o :o
                                    :_ :_ :_])))
          (it "returns true if board has no spaces left"
              (should= true (over? [:x :o :x
                                    :x :o :x
                                    :o :x :o ]))
              ))
