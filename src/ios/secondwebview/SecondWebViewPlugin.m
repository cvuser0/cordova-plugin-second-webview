#include <sys/types.h>
#include <sys/sysctl.h>

#import <Cordova/CDV.h>
#import "SecondWebViewPlugin.h"

@implementation SecondWebViewPlugin

@synthesize webViewController;

- (void)subscribeCallback:(CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
        @try {
            webViewFinishedCallBack = command.callbackId;
        }
        @catch (NSException *exception) {
            NSString* reason=[exception reason];
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString: reason];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }
    }];
}

- (void)show:(CDVInvokedUrlCommand*)command{
  NSString* url=(NSString*)[command.arguments objectAtIndex:0];
  NSLog(@"showwebViewView %@", url);
  [self.commandDelegate runInBackground:^{
    @try {
      dispatch_async(dispatch_get_main_queue(), ^{
          webViewController = [[WebViewController alloc] init];
          webViewController.delegate = self;
          webViewController.startPage = url;
          [self.viewController presentViewController:webViewController animated:YES completion:nil];
      });

      CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
      [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
    @catch (NSException *exception) {
      NSString* reason=[exception reason];
      CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString: reason];
      [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
  }];
}

- (void)hide:(CDVInvokedUrlCommand*)command{
  NSLog(@"hidewebViewView");
  [self.commandDelegate runInBackground:^{
    @try {

      dispatch_async(dispatch_get_main_queue(), ^{
        [self.viewController dismissViewControllerAnimated:YES completion:nil];
        [self dispose];
      });

      CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
      [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
    @catch (NSException *exception) {
      NSString* reason=[exception reason];
      CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString: reason];
      [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
  }];
}

-(void)webViewFinished{
  NSLog(@"webViewFinished");
  webViewController = nil;

  CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
  [self.commandDelegate sendPluginResult:pluginResult callbackId:webViewFinishedCallBack];
}

@end

@implementation WebViewController

@synthesize delegate;

- (id)init {
  self = [super init];
  return self;
}

- (void)viewDidDisappear:(BOOL)animated {
  NSLog(@"viewDidDisappear");
  [super viewDidDisappear:animated];
  [delegate webViewFinished];
  delegate = nil;
}
@end
