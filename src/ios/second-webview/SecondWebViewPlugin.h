#import <UIKit/UIKit.h>
#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVViewController.h>

@class WebViewController;

@protocol WebViewDelegate
  -(void)webViewFinished;
@end


@interface SecondWebViewPlugin : CDVPlugin<WebViewDelegate>
{
  @private NSString* webViewFinishedCallBack;
}
@property (nonatomic, retain) WebViewController* webViewController;

- (void)subscribeCallback:(CDVInvokedUrlCommand*)command;
- (void)show:(CDVInvokedUrlCommand*)command;
- (void)hide:(CDVInvokedUrlCommand*)command;

- (void)webViewFinished;

@end

@interface WebViewController : CDVViewController
{}
  @property (nonatomic, assign) id  delegate;
  - (void)viewDidDisappear:(BOOL)animated;
@end
