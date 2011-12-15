/*
 * Copyright 1998-2010 Linux.org.ru
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package ru.org.linux.poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.org.linux.topic.Topic;

@Service
public class PollPrepareService {
  @Autowired
  private PollDao pollDao;

  /**
   * Функция подготовки голосования
   * @param poll голосование
   * @return подготовленное голосование
   */
  public PreparedPoll preparePoll(Poll poll) {
    return new PreparedPoll(
            poll,
            pollDao.getMaxVote(poll),
            pollDao.getCountUsers(poll),
            pollDao.getPollVariants(poll, Poll.ORDER_VOTES)
    );
  }

  /**
   * Функция подготовки голосования
   * @param topic голосование
   * @return подготовленное голосование
   */
  public PreparedPoll preparePoll(Topic topic) throws PollNotFoundException {
    Poll poll = pollDao.getPollByTopicId(topic.getId());

    return new PreparedPoll(
            poll,
            pollDao.getMaxVote(poll),
            pollDao.getCountUsers(poll),
            pollDao.getPollVariants(poll, Poll.ORDER_VOTES)
    );
  }
}