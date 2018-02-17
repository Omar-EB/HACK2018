
import praw

reddit = praw.Reddit(client_id='ehpCFcRL2vQVOA',
                     client_secret='T98rCMYoiIS2DpEvf_EcaxYr_24',
                     username='uottawahacks',
                     password='hackslol',
                     user_agent='uottawahacks')

subreddit = reddit.subreddit('music')

for submission in subreddit.hot(limit=5000000):
    if(submission.stickied):
        print(submission.link_flair_text)
        if submission.link_flair_text == 'music streaming':
            print(submission.title)
            print('\n')




def find_by_genre(genrename):
    
    end
    







