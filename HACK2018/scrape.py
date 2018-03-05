import praw
import sys

reddit = praw.Reddit(client_id='ehpCFcRL2vQVOA',
                     client_secret='T98rCMYoiIS2DpEvf_EcaxYr_24',
                     username='uottawahacks',
                     password='hackslol',
                     user_agent='uottawahacks')

subreddit = reddit.subreddit('earthporn')

#for submission in subreddit.hot(limit=5):
    #if submission.link_flair_text == 'music streaming':
        #print(submission.title)
        #print('\n')
    #print(submission.url)

    



def find_by_type(type):
    submissions = []
    if type=='nature':
        subreddit = reddit.subreddit('earthporn')
    elif type=='cities':
        subreddit = reddit.subreddit('cityporn')
    elif type=='countryside':
        subreddit = reddit.subreddit('villageporn')
    elif type=='people':
        subreddit = reddit.subreddit('humanporn')
    elif type=='art':
        subreddit = reddit.subreddit('artporn')
    elif type=='cars':
        subreddit = reddit.subreddit('carporn')
    else :
        subreddit = reddit.subreddit('spaceporn')
    for submission in subreddit.hot(limit=6):
        submissions.append(submission)
    return submissions
    end


    
def main(type):
    submissions = find_by_type(type)
    for x in range(1, len(submissions)):
        print(submissions[x].url)    



type = str(sys.argv)
main(type)
